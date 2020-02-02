package com.example.kenrig.a8_contentresolverextended;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final Uri CONTENT_URL = Uri.parse("content://com.example.kenrig.a7_contentproviderresolverextended.ContactProvider/cpcontacts");
    TextView contactsTextView = null;
    EditText txtdelete,txtfind,txtadd;

    ContentResolver resolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolver = getContentResolver();

        contactsTextView = (TextView) findViewById(R.id.contactsTextView);
        txtdelete = (EditText) findViewById(R.id.txtdelete);
        txtfind = (EditText) findViewById(R.id.txtfind);
        txtadd = (EditText) findViewById(R.id.txtadd);


    }

    public void getContacts()
    {
        String[] projection = new String[]{"id","name"};
        Cursor cursor = resolver.query(CONTENT_URL,projection,null,null,null);

        String contactList = "";

        if (cursor.moveToFirst())
        {
            do
                {
                    String id = cursor.getString(cursor.getColumnIndex("id"));
                    String name = cursor.getString((cursor.getColumnIndex("name")));
                    contactList = contactList+id+" : "+name+"\n";
                }while (cursor.moveToNext());
        }

        contactsTextView.setText(contactList);
    }

    public void  deleteContact(View view)
    {
        String idToDelete = txtdelete.getText().toString();
        long idDeleted = resolver.delete(CONTENT_URL,"id=?",new String[]{idToDelete});
        getContacts();
    }

    public void findContact(View view)
    {
        String idToFind = txtfind.getText().toString();
        String[] projection = {"id","name"};

        Cursor cursor = resolver.query(CONTENT_URL,projection,"id=?",new String[]{idToFind},null);
        String contact = "";

        if(cursor.moveToNext())
        {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString((cursor.getColumnIndex("name")));
            contact = contact+id+" : "+name+"\n";
        }else
            {
                Toast.makeText(this,"Contact not found",Toast.LENGTH_SHORT).show();
            }
            contactsTextView.setText(contact);
    }

    public void addContact(View view)
    {
        String nameToAdd = txtadd.getText().toString();

        ContentValues values = new ContentValues();
        values.put("name",nameToAdd);

        resolver.insert(CONTENT_URL,values);

        getContacts();
    }

    public void showContacts(View view)
    {
        getContacts();
    }
}
