package com.ws.hugs.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.ws.hugs.data.event.EventMessage;

import org.apache.commons.net.ftp.FTPClient;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    private String hostName= "";
    private int port = 21;
    private String username ="xiaoming";
    private String password = "";

    public MyService() {
    }

    FTPClient ftpClient;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        EventBus.getDefault().register(this);

    }
    @Subscribe
    public void test(EventMessage eventMessage){
        if (eventMessage.getType()==EventMessage.START_FTP){
            if (ftpClient ==null){

            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }
}
