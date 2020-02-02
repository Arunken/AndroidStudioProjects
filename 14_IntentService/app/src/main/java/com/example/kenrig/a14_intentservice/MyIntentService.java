package com.example.kenrig.a14_intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class MyIntentService extends IntentService {
    /*
           IntentService  uses a separate work thread for executing the service unlike in the case of Service class.
           No need to stop the service. It will be automatically destroyed by the system when all the work is done by the service.
    */
    public MyIntentService() {
        super("MyService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "service is created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Toast.makeText(this, "service is started", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "service is stopped", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("MYIntentService :","from the onHandleIntent method!");
    }
}
