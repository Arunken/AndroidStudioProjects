package com.example.silverdoe.a26_gpslocation;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnstart,btnstop;
    TextView tvlat,tvlong;
    BroadcastReceiver breceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnstart = (Button) findViewById(R.id.btnstart);
        btnstop = (Button) findViewById(R.id.btnstop);
        tvlat = (TextView) findViewById(R.id.tvlat);
        tvlong = (TextView) findViewById(R.id.tvlong);

        if(runtimePermissionCheck())
        {
            enableButtons();
        }

    }

    private void enableButtons()
    {
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),GpsService.class);
                startService(i);

            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),GpsService.class);
                stopService(i);

            }
        });
    }

    public boolean runtimePermissionCheck()
    {
        boolean pcheck = false;
        if(Build.VERSION.SDK_INT>=23 && ContextCompat.checkSelfPermission(this, Manifest.permission
                .ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION},100);
            //pcheck=true;
        }
        else if(Build.VERSION.SDK_INT>=23 && ContextCompat.checkSelfPermission(this, Manifest.permission
                .ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)==
                        PackageManager.PERMISSION_GRANTED)
        {
            pcheck=true;
        }
        return pcheck;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED)
            {
                enableButtons();
            }
            else
                {
                    Toast.makeText(getApplicationContext(),"Please enable location settings!",Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(breceiver ==null)
        {
            breceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    tvlat.setText("Latitude : "+intent.getExtras().get("lat").toString());
                    tvlong.setText("Longitude : "+intent.getExtras().get("long").toString());

                }
            };
        }
        registerReceiver(breceiver,new IntentFilter("loc_update"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(breceiver!=null)
        {
            unregisterReceiver(breceiver);
        }
    }
}
