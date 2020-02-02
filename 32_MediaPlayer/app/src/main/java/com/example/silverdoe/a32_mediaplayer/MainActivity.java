package com.example.silverdoe.a32_mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(getApplicationContext(),R.raw.def);

    }

    public void mPlay(View view)
    {
        mp.start();
    }
    public void mPause(View view)
    {
        mp.pause();
    }
    public void mStop(View view)
    {
        mp.stop();
        this.finish();
    }
}
