package com.example.silverdoe.a24_alertdialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void showCustomDialog1(View view)
    {
       CustomDialog1 cd1 = new CustomDialog1(Main2Activity.this,"I have changed the text...");
       cd1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.CYAN));
       cd1.setCancelable(true);
       cd1.show();
    }
}
