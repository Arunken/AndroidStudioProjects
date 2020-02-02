package com.example.kenrig.a12_sharedpreferencestoolbarcolor;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnblue,btnpink;
    Toolbar custoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnblue = (Button) findViewById(R.id.btnblue);
        btnpink = (Button) findViewById(R.id.btnpink);
        custoolbar = (Toolbar)findViewById(R.id.toolbar);
        custoolbar.setTitle(getResources().getString(R.string.app_name));
        getWindow().setStatusBarColor(getPref());
        custoolbar.setBackgroundColor(getPref());


        btnblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorBlue,null));
                custoolbar.setBackgroundColor(getResources().getColor(R.color.colorBlue,null));
                setPref(getResources().getColor(R.color.colorBlue,null));
            }
        });
        btnpink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorPink,null));
                custoolbar.setBackgroundColor(getResources().getColor(R.color.colorPink,null));
                setPref(getResources().getColor(R.color.colorPink,null));
            }
        });
    }

    private void setPref(int color)
    {
        SharedPreferences spref = getSharedPreferences("toolBarColor",MODE_PRIVATE);
        SharedPreferences.Editor seditor = spref.edit();
        seditor.putInt("color",color);
        seditor.apply();
    }
    private int getPref()
    {
        SharedPreferences spref = getSharedPreferences("toolBarColor",MODE_PRIVATE);
        int selcolor = spref.getInt("color",getResources().getColor(R.color.colorPrimary,null));
        return selcolor;
    }


}
