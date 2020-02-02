package com.example.kenrig.a7_contentproviderresolverextended;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText contactNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactNameEditText = (EditText) findViewById(R.id.txtName);

    }

    public  void addName(View view)
    {
        String name = contactNameEditText.getText().toString();
        ContentValues values = new ContentValues();
        values.put(ContactProvider.name,name);

        Uri uri = getContentResolver().insert(ContactProvider.CONTENT_URL,values);
        Toast.makeText(getBaseContext(),"New Contact Added",Toast.LENGTH_SHORT).show();
    }
}
