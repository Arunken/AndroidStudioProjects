package com.example.silverdoe.a23_customtoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view)
    {
        View layout = getLayoutInflater().inflate(R.layout.toast,(ViewGroup) findViewById(R.id.toast));
        TextView tv =(TextView)layout.findViewById(R.id.textView);
        ImageView imgview =(ImageView)layout.findViewById(R.id.imageView);
        tv.setText("Hogwarts");
        imgview.setImageResource(R.drawable.dhimg);
        Toast t = new Toast(getApplicationContext());
        t.setDuration(Toast.LENGTH_SHORT);
        t.setGravity(Gravity.TOP,0,200);
        t.setView(layout);
        t.show();
    }
}
