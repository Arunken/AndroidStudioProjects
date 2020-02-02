package com.example.kenrig.a15_broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pbarBattery;
    private BroadcastReceiver bReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bReceiver = new BatteryBroadcast();

        pbarBattery = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    protected void onStart() {
        registerReceiver(bReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(bReceiver);

        super.onStop();
    }

    private class BatteryBroadcast extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            Toast.makeText(getApplicationContext(), "Battery Level : "+level, Toast.LENGTH_SHORT).show();
            pbarBattery.setProgress(level);

        }
    }
}
