package com.ws.hugs.common.utils;

import android.app.AlarmManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.bumptech.glide.util.FixedPreloadSizeProvider;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FTPManager {

//    private static String TAG = FTPManager.class.getSimpleName();
//
//    private final int maxAliveTime = 15;
//
//    private long lastActiveTIme = System.currentTimeMillis();
//
//    private FtpWorker ftpWorker = new FtpWorker();
//    private static FTPManager ftpmanager;
//
//    private Stack<FTPTask> stack = new Stack<>();
//
//    private volatile AtomicBoolean isRunning = new AtomicBoolean(false);
//    private volatile AtomicInteger aliveTime = new AtomicInteger(0);
//
//    static {
//        ftpmanager = new FTPManager();
//    }
//
//    private FTPManager() {
//    }
//
//    public static FTPManager getInstance() {
//        return ftpmanager;
//    }
//
//
//    public void addTask(List<FTPTask> list) {
//        if (list == null || list.size() == 0) return;
//        stack.addAll(list);
//        startAndCron();
//    }
//
//    public void addTask(FTPTask task) {
//        if (task == null) return;
//        stack.add(task);
//        startAndCron();
//    }
//
//    private void startAndCron() {
//
//        if (!isRunning.get()) {
//            connect();
//            aliveTime.set(maxAliveTime);
////            timer =new Timer();
////            timer.schedule(timerTask,0,1000);
//        }
//        while (!stack.empty()) {
//            t.execute(stack.pop());
//        }
//    }
//
//    ThreadPoolExecutor t = new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
//
//    class FtpWorker {
//        //        private String hostName = "139.198.191.101";
//        private String hostName = "192.168.3.229";
//        private int port = 2121;
//        private String username = "xiaoming";
//        private String password = "207113";
//        private FTPClient ftpClient;
//
//        private FtpWorker() {
//            ftpClient = new FTPClient();
//        }
//
//        public boolean login() {
//            String localCharset = "GBK";
//            try {
//                Log.i(TAG, "登录成功,进行初始化设置 ");
//                ftpClient.connect(hostName, port);
//                if (ftpClient.login(username, password)) {
//                    ftpClient.setBufferSize(1024 * 10);
//                    ftpClient.enterRemotePassiveMode();
//                    ftpClient.setControlEncoding("UTF-8");
//                    ftpClient.enterLocalPassiveMode();
//                    boolean b = ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//                    Log.i(TAG, "is binary  " + b);
////                    ftpClient.setty
//                    if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
//                        localCharset = "UTF-8";
//                    }
//                    ftpClient.setControlEncoding(localCharset);
//                    Log.i(TAG, "初始化设置完毕，准备就绪 ");
//                    return true;
//                } else {
//                    return false;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                return false;
//            }
//
//        }
//
//        public boolean relogin() {
//            if (ftpClient.isConnected()) {
//                try {
//                    disconnect();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return login();
//        }
//
//
//        /**
//         * 如果传输不成功 返回 null
//         *
//         * @param imageUrl
//         * @return
//         */
//        public synchronized InputStream receiveImage(String imageUrl, int time) {
//
//            if (ftpClient.isConnected()) {
//                try {
//
//                    Log.i("tag", "初始化成功 开始连接");
//
//                    return ftpClient.retrieveFileStream(new String(imageUrl.getBytes(), "ISO8859-1"));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    return null;
//                }
//            } else {
//                if (time <= 0) {
//                    Log.i("tag", "初始化失败 ");
//                    return null;
//                }
//                if (login()) {
//                    return receiveImage(imageUrl, --time);
//                } else {
//                    Log.w(TAG, "初始化失败初始化失败");
//                }
//
//                return null;
//            }
//        }
//
//        public void disconnect() throws IOException {
//            synchronized (FtpWorker.this) {
//                Log.w(TAG, "已经登出 ");
//                if (ftpClient.isConnected()) {
//                    ftpClient.logout();
//                    ftpClient.disconnect();
//                }
//            }
//
//        }
//    }
//
//
//    /**
//     * ftp下载
//     *
//     * @param url
//     * @param port
//     * @param username
//     * @param password
//     * @param filePath 存放文件的路径
//     * @param FTP_file 要下载的文件名
//     * @param SD_file  本地文件名
//     */
//    public static byte[] ftpDown(String url, int port, String username, String password, String filePath, String FTP_file, String SD_file) {
//        BufferedOutputStream buffOut = null;
//        String localCharset = "GBK";
//        FTPClient ftpClient = new FTPClient();
//        String returnMessage = "0";
//        long localsize = 0;
//        try {
//            ftpClient.connect(url, port);
//            boolean loginResult = ftpClient.login(username, password);
//            int returnCode = ftpClient.getReplyCode();
//            if (loginResult && FTPReply.isPositiveCompletion(returnCode)) {// 如果登录成功
//                ftpClient.setBufferSize(1024);
//                ftpClient.enterRemotePassiveMode();
//                ftpClient.enterLocalPassiveMode();
//                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//                ftpClient.setDataTimeout(1000*60);
//                ftpClient.setConnectTimeout(1000*60*2);
//                ftpClient.setControlKeepAliveReplyTimeout(1000*60*2);
//                ftpClient.setControlKeepAliveTimeout(1000*60*10);
//                ftpClient.setSoTimeout(1000*60*10);
//                ftpClient.setRemoteVerificationEnabled(false);
//                if (FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
//                    localCharset = "UTF-8";
//                }
//                ftpClient.setControlEncoding(localCharset);
//                if (!ftpClient.isConnected()){
//                    ftpClient.login(username, password);
//                }
//                FTPFile[] files = ftpClient.listFiles(new String(FTP_file.getBytes(), "iso-8859-1"));
//
//                if (files.length != 1) {
//                    Log.w(TAG, "不存在文件 "+files.length);
//                    return null;
//                }
//                long remoteFileSize = files[0].getSize();
//                Log.i(TAG,"远程数据大小为 "+remoteFileSize);
//                byte[] data = null;
//                while (remoteFileSize > localsize) {
//                    if (localsize != 0) {
//                        ftpClient.setRestartOffset(localsize);
//                    }
//                    InputStream inputStream = ftpClient.retrieveFileStream(new String(FTP_file.getBytes(), "ISO8859-1"));
//                    if (inputStream==null) {
//                        Log.w(TAG,"inputStream 没有获取到资源");
//                        continue;
//                    }
//                    int count = inputStream.available();
//                    System.out.println("count:" + count);
//
//                    while (count > 0) {// 获取到的大小小于文件大小也进入
//                        byte[] b = new byte[count];
//                        inputStream.read(b);
//                        Log.v(TAG," "+ b.length);
//                        data = byteMerger(b, data);
//                        count = inputStream.available();
//                        localsize = data.length;
//                    }
//                }
//                ftpClient.logout();
//                ftpClient.disconnect();
//                return data;
//            } else {// 如果登录失败
//                returnMessage = "0";
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("FTP客户端出错！", e);
//        } finally {
//            //IOUtils.closeQuietly(fis);
//            try {
//                ftpClient.disconnect();
//            } catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException("关闭FTP连接发生异常！", e);
//            }
//        }
//        return null;
//    }
//
//    public static byte[] byteMerger(byte[] byte1, byte[] byte2){
//        if (byte1==null && byte2!=null){
//            return byte2;
//        }
//
//        if (byte2 == null && byte1!=null){
//            return byte1;
//        }
//
//        if (byte2 == null && byte1 ==null){
//            return null;
//        }
//        byte[] byte3 = new byte[byte1.length+byte2.length];
//        System.arraycopy(byte2, 0, byte3, 0, byte2.length);
//        System.arraycopy(byte1, 0, byte3, byte2.length, byte1.length);
//
////        System.arraycopy(byte1, 0, byte3, 0, byte1.length);
////        System.arraycopy(byte2, 0, byte3, byte1.length, byte2.length);
//        return byte3;
//    }
//
//    public static byte[] ftpDown(String FTP_file) {
//        String hostName = "139.198.191.101";
//        int port = 2121;
//        String username = "xiaoming";
//        String password = "207113";
//        return ftpDown(hostName, port, username, password, "", FTP_file, "");
//    }
//
//    public Bitmap addImage(String imgUrl) {
//        InputStream inputStream = ftpWorker.receiveImage(imgUrl, 3);
////        if (inputStream == null) throw new IllegalStateException("数据不存在或连接失败 "+imgUrl);
//        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//        return bitmap;
//    }
//
//
//    private void connect() {
//        isRunning.set(true);
//        ftpWorker.login();
//    }
//
//    private void disConnect() throws IOException {
//        isRunning.set(false);
//        ftpWorker.disconnect();
//    }
//
//    public FTPTask genearateTask(String url, FTPCallback callback) {
//        return new FTPTask(url, callback);
//    }
//
//
//    class FTPTask extends Thread {
//        String imgUrl;
//        FTPCallback ftpCallback;
//
//        public FTPTask(String imgUrl, FTPCallback ftpCallback) {
//            this.ftpCallback = ftpCallback;
//            this.imgUrl = imgUrl;
//        }
//
//        void init() {
//            if (!isRunning.get()) {
//                connect();
//            }
//            aliveTime.set(maxAliveTime);
//        }
//
//        void check() {
//            if (stack.empty()) {
//                try {
//                    disConnect();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        @Override
//        public void run() {
//            super.run();
//            init();
//            Bitmap bitmap = addImage(imgUrl);
//            ftpCallback.onResponse(bitmap);
////            check();
//        }
//    }

//    public static void main(String[] args) {
//        FTPManager instance = FTPManager.getInstance();
//        FTPManager.FTPTask 成功下载到本地 = instance.genearateTask(new FTPCallback() {
//
//
//            @Override
//            public void onResponse(Bitmap bitmap){
//                if (bitmap ==null ) {
////                    System.out.println("路径不存在");
//                    return;
//                }
//
//                System.out.println("下载成功" +bitmap.getByteCount() );
//
//            }
//        }, "G:\\12.jpg");
//        ArrayList< FTPManager.FTPTask> objects = new ArrayList< FTPManager.FTPTask>();
//        objects.add(成功下载到本地);
//        instance.addTask(objects);
//    }
}
