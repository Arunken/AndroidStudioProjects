package com.example.silverdoe.a17_menus;

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

    public void openOption(View view)
    {
        Intent i = new Intent(getApplicationContext(),ActivityOption.class);
        startActivity(i);
    }
    public void openContext(View view)
    {
        Intent i = new Intent(getApplicationContext(),ActivityContext.class);
        startActivity(i);
    }
    public void openPopup(View view)
    {
        Intent i = new Intent(getApplicationContext(),ActivityPopup.class);
        startActivity(i);
    }
}
