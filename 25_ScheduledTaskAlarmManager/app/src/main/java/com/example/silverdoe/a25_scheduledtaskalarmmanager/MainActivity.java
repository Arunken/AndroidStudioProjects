package com.example.silverdoe.a25_scheduledtaskalarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TimePicker tpicker;
    Button btnset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tpicker = (TimePicker)findViewById(R.id.timepicker);
        btnset = (Button) findViewById(R.id.button);

        btnset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();

                if(Build.VERSION.SDK_INT>=23)
                {
                    c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),
                            c.get(Calendar.DAY_OF_MONTH),tpicker.getHour(),tpicker.getMinute(),0);
                }
                else
                    {
                        c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH),
                                tpicker.getCurrentHour(),tpicker.getCurrentMinute(),0);
                    }
                    setAlarm(c.getTimeInMillis());
            }
        });

    }

    public void setAlarm(long currentTimeMillis)
    {
        AlarmManager alarmmgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(this,AlarmReceiver.class);

        PendingIntent pintent = PendingIntent.getBroadcast(this,0,i,0);
        alarmmgr.setRepeating(AlarmManager.RTC_WAKEUP,currentTimeMillis,AlarmManager.INTERVAL_DAY,pintent);
        Toast.makeText(getApplicationContext(),"Alarm Set!",Toast.LENGTH_SHORT).show();
    }
}
