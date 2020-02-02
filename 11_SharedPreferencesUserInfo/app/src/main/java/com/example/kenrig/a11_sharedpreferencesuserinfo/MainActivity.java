package com.example.kenrig.a11_sharedpreferencesuserinfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtuname,txtpass;
    TextView tvdisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtuname = (EditText) findViewById(R.id.txtuname);
        txtpass =(EditText) findViewById(R.id.txtpass);
        tvdisplay = (TextView) findViewById(R.id.textView);
    }

    public void saveData(View view)
    {
        String name = txtuname.getText().toString();
        String password = txtpass.getText().toString();

        SharedPreferences spref = getSharedPreferences("udata", Context.MODE_PRIVATE);
        SharedPreferences.Editor seditor = spref.edit();
        seditor.putString("username",name);
        seditor.putString("password",password);
        seditor.apply();

        Toast.makeText(getApplicationContext(),"saved",Toast.LENGTH_SHORT).show();
    }

    public void getData(View view)
    {
        SharedPreferences spref = getSharedPreferences("udata",Context.MODE_PRIVATE);
        String name = spref.getString("username","");
        String pwd = spref.getString("password","");
        tvdisplay.setText(name+"\n"+pwd);
    }
}
