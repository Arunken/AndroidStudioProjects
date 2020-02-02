package com.example.kenrig.a1_intentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv1 =(TextView) findViewById(R.id.textView);
        tv2 =(TextView) findViewById(R.id.textView2);

        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra("bund");

        tv1.setText(b.getString("nme"));
        tv2.setText(b.getString("pwd"));
    }
}
