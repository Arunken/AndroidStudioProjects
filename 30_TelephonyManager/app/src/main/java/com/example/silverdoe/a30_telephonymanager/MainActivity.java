package com.example.silverdoe.a30_telephonymanager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvDisp;
    String imei="";
    TelephonyManager telmgr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // For uniquely identifying android devices 6 and above as getting Imei is deprecated
        //Settings.Secure.ANDROID_ID

        tvDisp = (TextView) findViewById(R.id.textView);
    }

    public void getInfo(View view)
    {
        telmgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        String operatorName = "\nOperator ID : "+telmgr.getNetworkOperator();
        operatorName = operatorName+"\nOperator Name :"+telmgr.getNetworkOperatorName();

        int phoneTypecode = telmgr.getPhoneType();
        String phoneType ="";
        switch (phoneTypecode)
        {
            case TelephonyManager.PHONE_TYPE_CDMA:
                phoneType ="\nPhone Type : CDMA\n";
                break;
            case TelephonyManager.PHONE_TYPE_GSM:
                phoneType ="\nPhone Type : GSM\n";
                break;
            case TelephonyManager.PHONE_TYPE_SIP:
                phoneType ="\nPhone Type : SIP\n";
                break;
            case TelephonyManager.PHONE_TYPE_NONE:
                phoneType ="\nPhone Type : NONE\n";
                break;
        }

        boolean isroaming = telmgr.isNetworkRoaming();
        String phonedetails="";
        if(isroaming)
        {
            phonedetails = "Roaming : Yes\n";
        }
        else
            {
                phonedetails = "Roaming : No\n";
            }
        int callstatecode = telmgr.getCallState();
        String callstate = "";
        switch (callstatecode)
        {
            case TelephonyManager.CALL_STATE_IDLE:
                callstate = "Idle state!\n";
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                callstate = "In use!\n";
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                callstate = "Ringing\n";
                break;
        }
        int simstatecode = telmgr.getSimState();
        String simstate="";
        switch (simstatecode)
        {
            case TelephonyManager.SIM_STATE_ABSENT:
                simstate = "Sim State : Absent\n";
                break;
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                simstate = "Sim State : Network Locked\n";
                break;
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                simstate = "Sim State : Pin Required\n";
                break;
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                simstate = "Sim State : Puk Required\n";
                break;
            case TelephonyManager.SIM_STATE_READY:
                simstate = "Sim State : Ready\n";
                break;
            case TelephonyManager.SIM_STATE_UNKNOWN:
                simstate = "Sim State : Unknown\n";
                break;
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)== PackageManager.PERMISSION_GRANTED)
        {
            imei=telmgr.getDeviceId();
        }
        else
            {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_PHONE_STATE},100);
            }

        tvDisp.setText(operatorName+phoneType+phonedetails+callstate+simstate+"\nImei"+imei);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)== PackageManager.PERMISSION_GRANTED)
        {
            //imei=telmgr.getDeviceId();
        }
    }
}
