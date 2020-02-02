package com.example.silverdoe.a24_alertdialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class CustomDialog1 extends Dialog implements View.OnClickListener {

    public Activity a;
    public Dialog d;
    TextView tv;
    String text="";

    public CustomDialog1(Activity a,String text)
    {
        super(a);
        this.text=text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.custom_dialog);

        tv = (TextView) findViewById(R.id.textView);

        tv.setText(text);
    }

    @Override
    public void onClick(View v) {

        this.dismiss();
    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
