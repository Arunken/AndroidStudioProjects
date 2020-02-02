package com.example.silverdoe.a25_scheduledtaskalarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MediaPlayer mplayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mplayer.start();
    }
}
