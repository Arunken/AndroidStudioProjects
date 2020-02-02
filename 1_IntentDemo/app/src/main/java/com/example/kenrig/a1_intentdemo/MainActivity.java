package com.example.kenrig.a1_intentdemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtname,txtpass;
    Button btnsubmit,btncall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    txtname =(EditText) findViewById(R.id.txtname);
    txtpass = (EditText) findViewById(R.id.txtpassword);
    btnsubmit = (Button) findViewById(R.id.button);
    btncall = (Button) findViewById(R.id.button2);

    btnsubmit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(getApplicationContext(),"Button Clicked",Toast.LENGTH_SHORT).show();
            String name = txtname.getText().toString();
            String pass = txtpass.getText().toString();

            Toast.makeText(getApplicationContext(),"Name : "+name+", Paasword : "+pass,Toast.LENGTH_SHORT).show();

            Intent i = new Intent(MainActivity.this,Main2Activity.class);
            Bundle bun = new Bundle();
            bun.putString("nme",name);
            bun.putString("pwd",pass);
            i.putExtra("bund",bun);
            startActivity(i);
        }
    });

    btncall.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent callintent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:8943008651"));// to dial a call
            Intent callintent1 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:8943008651")); // to call a number need to add permissions
            startActivity(callintent1);

        }
    });

    }
}
