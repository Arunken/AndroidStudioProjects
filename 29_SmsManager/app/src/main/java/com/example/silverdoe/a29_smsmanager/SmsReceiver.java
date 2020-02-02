package com.example.silverdoe.a29_smsmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            SmsMessage[] smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            String str="";
            for (SmsMessage message : smsMessages) {
                str = str+message.getOriginatingAddress();
                str = str+": ";
                str = str+message.getMessageBody();
                str = str+"\n";

                Intent bintent = new Intent();
                bintent.setAction("SMS_RECEIVED_ACTION");
                bintent.putExtra("message",str);
                context.sendBroadcast(bintent);
            }
        }
    }
}
