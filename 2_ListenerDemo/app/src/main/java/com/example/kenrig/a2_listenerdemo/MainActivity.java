package com.example.kenrig.a2_listenerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtfname,txtlname;
    Button btnsubmit;
    TextView tvtouchme;
    String fname,lname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtfname =(EditText) findViewById(R.id.txtfname);
        txtlname =(EditText) findViewById(R.id.txtlname);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);
        tvtouchme = (TextView)findViewById(R.id.textView);

        // Focus listener
        txtfname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                 fname = txtfname.getText().toString();
                 
                 if(hasFocus==false)// focus is lost
                 {
                     if(fname.equals("") || fname.equals(null))
                     {
                         Toast.makeText(MainActivity.this, "Enter the first name", Toast.LENGTH_SHORT).show();
                     }
                 }
            }
        });

        // Key Listener
        txtlname.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                char kchar = event.getDisplayLabel();
                int kcode = event.getKeyCode();
                int ksource = event.getSource();
                int kcount = event.getRepeatCount();

                Toast.makeText(getApplicationContext(),"kchar :"+kchar+", kcode : "+kcode+", ksource : "+ksource+", kcount : "+kcount,Toast.LENGTH_LONG).show();
                return false;
            }
        });

        // Touch Listener
        tvtouchme.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                float rawx = event.getRawX();
                float rawy = event.getRawY();
                float x = event.getX();
                float y = event.getY();
                Toast.makeText(getApplicationContext(),"X : "+x+", Y : "+y,Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}
