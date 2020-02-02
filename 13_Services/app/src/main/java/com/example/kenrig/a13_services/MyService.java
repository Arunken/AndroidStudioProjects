package com.example.kenrig.a13_services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyService extends Service { // extending service uses the same thread of the application causing application to lag.

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(),"Service created",Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(),"Service started",Toast.LENGTH_SHORT).show();
        String message =intent.getStringExtra("msg");
        Toast.makeText(getApplicationContext(),"Passed text : "+message,Toast.LENGTH_SHORT).show();
        //stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(),"Service destroyed",Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
