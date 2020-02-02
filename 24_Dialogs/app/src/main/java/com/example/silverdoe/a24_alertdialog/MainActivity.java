package com.example.silverdoe.a24_alertdialog;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnalert,btnprogress,btndate,btntime,btncusdialog;
    int year,month,day,hour,minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnalert = (Button) findViewById(R.id.button);
        btnprogress =(Button) findViewById(R.id.button2);
        btndate = (Button) findViewById(R.id.btnDate);
        btntime = (Button) findViewById(R.id.btntime);
        btncusdialog = (Button) findViewById(R.id.btncustom);
        btnalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Alert Dialog");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("This is an example for alert dialog in android").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"You have pressed ok!",Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        btnprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pdialog = new ProgressDialog(MainActivity.this);
                pdialog.setTitle("Loading....");
                pdialog.setMessage("This is a progress dialog! \n Use this wherever needed.");
                pdialog.setIndeterminate(false);
                pdialog.setMax(100);
                pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pdialog.setCancelable(true);
                pdialog.setCanceledOnTouchOutside(false);
                pdialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pdialog.dismiss();
                    }
                });
                pdialog.show();

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<100;i++)
                        {
                            try
                            {
                                Thread.sleep(400);
                            }catch (Exception ex)
                            {
                                Toast.makeText(getApplicationContext(),"Exception Occured on thread",Toast.LENGTH_SHORT).show();
                            }
                            pdialog.incrementProgressBy(1);
                            pdialog.incrementSecondaryProgressBy(5);
                        }
                    }
                });
                t.start();

            }
        });

        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog ddialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int fyear =year;
                        int fmonth = month+1;
                        int fday=dayOfMonth;

                        Toast.makeText(getApplicationContext(),"Year : "+fyear+", Month : "+fmonth+", Day : "+fday,Toast.LENGTH_SHORT).show();
                    }
                }, year, month, day);
                ddialog.show();
            }
        });

        btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                TimePickerDialog tdialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        int fhour = hourOfDay;
                        int fminute = minute;
                        String time;
                        if(fhour<12)
                        {
                            time = "AM";
                        }
                        else
                         {
                             fhour = fhour-12;
                             time = "PM";
                         }

                        Toast.makeText(MainActivity.this, fhour+" : "+fminute+" "+time, Toast.LENGTH_SHORT).show();
                    }
                }, hour, minute, android.text.format.DateFormat.is24HourFormat(MainActivity.this));
                tdialog.show();
            }
        });

        btncusdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });

    }
}
