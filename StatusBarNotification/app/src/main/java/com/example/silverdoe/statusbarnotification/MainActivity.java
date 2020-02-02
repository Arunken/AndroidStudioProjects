package com.example.silverdoe.statusbarnotification;

import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showNotif(View view)
    {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder noti = new NotificationCompat.Builder(this);
        noti.setContentTitle("Fused Location");
        noti.setContentText("This is a notification");
        noti.setSmallIcon(R.drawable.ic_launcher_foreground);

        notificationManager.notify(1234, noti.build());
    }
}
