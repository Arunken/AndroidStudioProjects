package com.example.kenrig.a3_sqlitecruds;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    SQLiteDatabase sqldb;
    ListView lv;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // CREATE OR OPEN DATABASE
        sqldb = openOrCreateDatabase("DETAILS.db", Context.MODE_PRIVATE,null);
        sqldb.execSQL("CREATE TABLE IF NOT EXISTS USERS(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,name VARCHAR,gender VARCHAR,city VARCHAR,hobbies VARCHAR)");


        lv = (ListView) findViewById(R.id.list1);
        List<String> names=null;
        names = new ArrayList<>();

        try
        {
            String sqlselect = "SELECT * FROM USERS";
            cursor = sqldb.rawQuery(sqlselect,null);
            while(cursor.moveToNext())
            {
                names.add(cursor.getString(1));
                //Toast.makeText(getApplicationContext(),cursor.getString(1),Toast.LENGTH_SHORT).show();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,names);
            lv.setAdapter(adapter);
        }
        catch(SQLiteException exsel)
        {
            Toast.makeText(Main2Activity.this, "Failed to fetch", Toast.LENGTH_SHORT).show();
            Log.e(" error",exsel.getMessage());
        }
    }
}
