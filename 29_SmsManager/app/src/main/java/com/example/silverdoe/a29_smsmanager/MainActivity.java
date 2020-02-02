package com.example.silverdoe.a29_smsmanager;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSend;
    EditText txtNum,txtMsg;
    IntentFilter iFilter;
    TextView tvDisplay;
    BroadcastReceiver sentReceiver,deliverReceiver,receiveSmsReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = (Button) findViewById(R.id.btnsend);
        txtNum = (EditText) findViewById(R.id.txtNum);
        txtMsg = (EditText) findViewById(R.id.txtmsg);
        txtNum.setText("15555215554");

        iFilter = new IntentFilter();
        iFilter.addAction("SMS_RECEIVED_ACTION");

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    public void sendMessage(View view)
    {
        boolean pcheck= false;
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},100);
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},100);
        }
        else if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)== PackageManager.PERMISSION_GRANTED)
        {
            pcheck = true;
        }

        if(pcheck)
        {
            String number = txtNum.getText().toString();
            String message = txtMsg.getText().toString();

            PendingIntent pintentsend = PendingIntent.getBroadcast(this,0,new Intent("SMS_SENT"),0);
            PendingIntent pintentdelivered = PendingIntent.getBroadcast(this,0,new Intent("SMS_DELIVERED"),0);

            SmsManager smsmgr = SmsManager.getDefault();
            smsmgr.sendTextMessage(number,null,message,pintentsend,pintentdelivered);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED)
            {
                sendMessage(getCurrentFocus());
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Please accept permission request!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        sentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(context,"message send",Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(context,"Generic failure",Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(context,"no service",Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(context,"null pdu",Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(context,"Radio off",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        deliverReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(context,"message delivered",Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(context,"message not delivered",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        receiveSmsReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                tvDisplay = (TextView) findViewById(R.id.tvdisplay);
                tvDisplay.setText(tvDisplay.getText().toString()+"\n\n"+intent.getExtras().getString("message"));
            }
        };
        registerReceiver(sentReceiver,new IntentFilter("SMS_SENT"));
        registerReceiver(deliverReceiver,new IntentFilter("SMS_DELIVERED"));
        registerReceiver(receiveSmsReceiver,new IntentFilter("SMS_RECEIVED_ACTION"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(deliverReceiver);
        unregisterReceiver(sentReceiver);
    }
}
