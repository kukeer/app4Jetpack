package com.ws.hugs;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshInitializer;
import com.ws.hugs.api.RequestManager;
import com.ws.hugs.common.utils.GsonUtils;
import com.ws.hugs.common.utils.PhoneInfo;
import com.ws.hugs.common.utils.SPUtils;
import com.ws.hugs.common.utils.SingleWorker;
import com.xcheng.retrofit.CallAdapterFactory;
import com.xcheng.retrofit.DownloadCallAdapterFactory;
import com.xcheng.retrofit.ReplaceUrlCallFactory;
import com.xcheng.retrofit.RetrofitFactory;

import org.json.JSONObject;

import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import leakcanary.LeakCanary;
import okhttp3.HttpUrl;
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
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xcrash.ICrashCallback;
import xcrash.TombstoneManager;
import xcrash.TombstoneParser;
import xcrash.XCrash;

public class HugApplication extends Application {

    private String TAG = this.getClass().getSimpleName();
    private static HugApplication application;

    public static HugApplication getApplication(){
        return application;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        application =this;
        init();

    }
    public static int phoneWidth = 0;
    public static int phoneHeight = 0;
    private void init() {

        SPUtils.initSp(getSharedPreferences("APPLICATION",Context.MODE_PRIVATE));
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//        SingleWorker.initContext(this);
        phoneWidth = wm.getDefaultDisplay().getWidth();
        phoneHeight = wm.getDefaultDisplay().getHeight();
        try{
            RequestManager.initOkHttp();
        }catch (Exception e){
            e.printStackTrace();
        }
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
                //开始设置全局的基本参数（可以被下面的DefaultRefreshHeaderCreator覆盖）
                layout.setReboundDuration(1000);
//                layout.setReboundInterpolator(new DropBounceInterpolator());
                layout.setFooterHeight(100);
                layout.setDisableContentWhenLoading(false);
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            }
        });

        //全局设置默认的 Header
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //开始设置全局的基本参数（这里设置的属性只跟下面的MaterialHeader绑定，其他Header不会生效，能覆盖DefaultRefreshInitializer的属性和Xml设置的属性）
                layout.setEnableHeaderTranslationContent(false);

                return new MaterialHeader(context).setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.design_default_color_primary_variant);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        ICrashCallback callback = new ICrashCallback() {

            @Override
            public void onCrash(String logPath, String emergency) throws Exception {

                Log.d(TAG, "log path: " + (logPath != null ? logPath : "(null)") + ", emergency: " + (emergency != null ? emergency : "(null)"));

                if (emergency != null) {
                    debug(logPath, emergency);
                } else {
                    TombstoneManager.appendSection(logPath, "expanded_key_1", "expanded_content");
                    TombstoneManager.appendSection(logPath, "expanded_key_2", "expanded_content_row_1\nexpanded_content_row_2");

                    // Invalid. (Do NOT include multiple consecutive newline characters ("\n\n") in the content string.)
                    // TombstoneManager.appendSection(logPath, "expanded_key_3", "expanded_content_row_1\n\nexpanded_content_row_2");

                    debug(logPath, null);
                }
            }
        };

        // Initialize xCrash.
        XCrash.init(this, new XCrash.InitParameters()
                .setAppVersion("1.2.3-beta456-patch789")
                .setJavaRethrow(true)
                .setJavaLogCountMax(10)
                .setJavaDumpAllThreadsWhiteList(new String[]{"^main$", "^Binder:.*", ".*Finalizer.*"})
                .setJavaDumpAllThreadsCountMax(10)
                .setJavaCallback(callback)
                .setNativeRethrow(true)
                .setNativeLogCountMax(10)
                .setNativeDumpAllThreadsWhiteList(new String[]{"^xcrash\\.sample$", "^Signal Catcher$", "^Jit thread pool$", ".*(R|r)ender.*", ".*Chrome.*"})
                .setNativeDumpAllThreadsCountMax(10)
                .setNativeCallback(callback)
                .setAnrRethrow(true)
                .setAnrLogCountMax(10)
                .setAnrCallback(callback)
                .setPlaceholderCountMax(3)
                .setPlaceholderSizeKb(512)
                .setLogFileMaintainDelayMs(1000));

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (File file : TombstoneManager.getAllTombstones()) {
                    sendThenDeleteCrashLog(file.getAbsolutePath(), null);
                }
            }
        }).start();
    }

    private void sendThenDeleteCrashLog(String logPath, String emergency) {
        // Parse
        //Map<String, String> map = TombstoneParser.parse(logPath, emergency);
        //String crashReport = new JSONObject(map).toString();

        // Send the crash report to server-side.
        // ......

        // If the server-side receives successfully, delete the log file.
        //
        // Note: When you use the placeholder file feature,
        //       please always use this method to delete tombstone files.
        //
        //TombstoneManager.deleteTombstone(logPath);
    }

    private void debug(String logPath, String emergency) {
        FileWriter fileWriter = null;
        try {

            File file = new File(getApplicationContext().getFilesDir() + "/tombstones/debug.json");
            file.createNewFile();
            fileWriter = new FileWriter(file, false);
            fileWriter.write(new JSONObject(TombstoneParser.parse(logPath, emergency)).toString());


        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

