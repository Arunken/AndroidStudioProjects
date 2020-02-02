package com.example.kenrig.databindingtextview;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kenrig.databindingtextview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Databinding class name depends on the activity xml filename. Open activity xml
        // file to see how databinding is implemented.
        ActivityMainBinding avb = DataBindingUtil.setContentView(this,R.layout.activity_main);
        User user = new User("Arun","Ken");
        avb.setUser(user);
        // Enable databinding in module app default config
        //  dataBinding
        //                {
        //                    enabled=true
        //                }

    }
}
