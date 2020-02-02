package com.example.kenrig.a13_services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startMC(View view)
    {
        Intent i = new Intent(getApplicationContext(),MyService.class);
        i.putExtra("msg","hello there");
        startService(i);
    }
    public void stopMC(View view)
    {
        Intent i = new Intent(getApplicationContext(),MyService.class);
        stopService(i);
    }
}
