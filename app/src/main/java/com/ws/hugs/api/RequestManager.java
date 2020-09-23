package com.ws.hugs.api;

import android.util.Log;

import com.ws.hugs.HugApplication;
import com.xcheng.retrofit.CallAdapterFactory;
import com.xcheng.retrofit.DownloadCallAdapterFactory;
import com.xcheng.retrofit.RetrofitFactory;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    private static String TAG = RequestManager.class.getClass().getSimpleName();
    //    public ConcurrentHashMap<String,Call> callMap = new ConcurrentHashMap<String, Call>();
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
//        if ()
        return retrofit;
    }

    public HttpLoggingInterceptor getLoginInterceptor(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }
    private static LogInterceptor getLogInterceptor(){

        return new LogInterceptor();
    }
    public static void initOkHttp() throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException, IOException, CertificateException {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.writeTimeout(60, TimeUnit.SECONDS);
        httpClient.addInterceptor(getLogInterceptor());  // <-- this is the important line!


        SSLContext sslContext = SSLContext.getInstance("SSL");
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        KeyStore keyStore = readKeyStore();
        trustManagerFactory.init(keyStore);
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, "zzhiot@sina.com".toCharArray());
        sslContext.init(keyManagerFactory.getKeyManagers(),trustManagerFactory.getTrustManagers(), new SecureRandom());
        httpClient.sslSocketFactory(sslContext.getSocketFactory());
//        httpClient.addNetworkInterceptor(new NetWorkIntce)
//        new GsonBuilder().registerTypeAdapter()

        OkHttpClient client = httpClient.build();
      //your method to obtain KeyStore


        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zzhiot.top:10000/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CallAdapterFactory.INSTANCE)
                .addCallAdapterFactory(DownloadCallAdapterFactory.INSTANCE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        RetrofitFactory.DEFAULT = retrofit;
        Log.i(TAG,"初始化成功 当前 reftofit 为 "+retrofit);
    }

    private static KeyStore readKeyStore() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());

        // get user password and file input stream
        char[] password = "zzhiot@sina.com".toCharArray();
        InputStream open = HugApplication.getApplication().getApplicationContext().getAssets().open("private.bks");
        ks.load(open, password);
        open.close();
        return ks;

    }

    public static MM131RequestCenter getRequestCenter(){
        return RetrofitFactory.create(MM131RequestCenter.class);
    }

    static class LogInterceptor implements Interceptor {
        //        public static final String TAG = "LogInterceptor.java";
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //the request url
            String url = request.url().toString();
            //the request method
            String method = request.method();
            long t1 = System.nanoTime();
            Log.d(TAG,String.format(Locale.getDefault(),"Sending %s request [url = %s]",method,url));
            //the request body
            RequestBody requestBody = request.body();
            if(requestBody!= null) {
                StringBuilder sb = new StringBuilder("Request Body [");
                Buffer buffer = new okio.Buffer();
                requestBody.writeTo(buffer);
                Charset charset = Charset.forName("UTF-8");
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(charset);
                }
                if(isPlaintext(buffer)){
                    sb.append(buffer.readString(charset));
                    sb.append(" (Content-Type = ").append(contentType.toString()).append(",")
                            .append(requestBody.contentLength()).append("-byte body)");
                }else {
                    sb.append(" (Content-Type = ").append(contentType.toString())
                            .append(",binary ").append(requestBody.contentLength()).append("-byte body omitted)");
                }
                sb.append("]");
                Log.d(TAG, String.format(Locale.getDefault(), "%s %s", method, sb.toString()));
            }
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            //the response time
            Log.d(TAG,String.format(Locale.getDefault(),"Received response for [url = %s] in %.1fms",url, (t2-t1)/1e6d));

            //the response state
            Log.d(TAG,String.format(Locale.CHINA,"Received response is %s ,message[%s],code[%d]",response.isSuccessful()?"success":"fail",response.message(),response.code()));

            //the response data
            ResponseBody body = response.body();

            BufferedSource source = body.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            Charset charset = Charset.defaultCharset();
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            String bodyString = buffer.clone().readString(charset);
            Log.d(TAG,String.format("Received response json string [%s]",bodyString));

            return response;
        }

        boolean isPlaintext(Buffer buffer){
            try {
                Buffer prefix = new Buffer();
                long byteCount = buffer.size() < 64 ? buffer.size() : 64;
                buffer.copyTo(prefix, 0, byteCount);
                for (int i = 0; i < 16; i++) {
                    if (prefix.exhausted()) {
                        break;
                    }
                    int codePoint = prefix.readUtf8CodePoint();
                    if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                        return false;
                    }
                }
                return true;
            } catch (EOFException e) {
                return false; // Truncated UTF-8 sequence.
            }
        }

    }

}
