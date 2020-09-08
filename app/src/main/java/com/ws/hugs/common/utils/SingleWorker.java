package com.ws.hugs.common.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class SingleWorker {
//    private static String TAG = SingleWorker.class.getSimpleName();
//
//    private static SingleWorker singleWorker = new SingleWorker();
//    public static SingleWorker getInstance(){
//        return singleWorker;
//    }
//    // 0
//    private static AtomicInteger status =new AtomicInteger(0);
//    private static int countTask=0;
//    private static final int perStop =5;
//    private static void changeFree(){
//        status.set(0);
//        countTask++;
//    }
//    private static void changeRunning(){
//        Log.i(TAG,"处于运行态");
//        status.set(1);
//    }
//
//    static Context context;
//    Stack<FTPTask> stack =new Stack<>();
//
//    ThreadGroup threadGroup = new ThreadGroup(Thread.currentThread().getThreadGroup(),"SINGLE");
//
//    public static void initContext(Context  context1){
//        context =context1;
//    }
//
//    private SingleWorker() {
//        threadGroup.setMaxPriority(1);
//    }
//
//    public FTPTask generateTask(String url, FTPCallback ftpCallback){
//        return new FTPTask(url,ftpCallback);
//    }
//    class FTPTask implements Runnable{
//        String img;
//        FTPCallback ftpCallback;
//
//
//
//        public FTPTask(String img, FTPCallback ftpCallback) {
//            this.ftpCallback = ftpCallback;
//            this.img = img;
//        }
//
//
//        @Override
//        public void run() {
//            byte[] data = FTPManager.ftpDown(img);
//            Log.i(TAG,"接收到的数组大小为 "+data.length);
//            Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
//            Log.i(TAG,"下载的inputStrem"+bitmap.getByteCount());
//            changeFree();
//            Application application = (Application)context;
//            Handler handler = new Handler(application.getMainLooper());
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    ftpCallback.onResponse(bitmap);
//                }
//            });
//
//
//        }
//    }
//
//    public synchronized void execute(FTPTask task){
//        stack.push(task);
//        while (!stack.isEmpty()){
//            if ((status.get()==0) && threadGroup.activeCount()<5){
//                changeRunning();
//                Thread thread = new Thread(threadGroup, stack.pop());
//                thread.start();
//            }
//        }
//
//    }

}
