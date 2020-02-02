package com.example.kenrig.a4_contentvalues;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtId,txtName;
    RadioButton rbfemale,rbmale;
    RadioGroup rgroup;
    Spinner spinnercity;
    CheckBox chkswimming,chkhiking;

    Button btnsearch,btnadd,btnupdate,btndelete,btnviewall;

    SQLiteDatabase sqldb;
    Cursor cursor;

    String id,name,gender,city,hobbies;
    String[] cities = {"Bangalore","Chennai","Calicut","Chennai","Pune","Mumbai"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CREATE OR OPEN DATABASE
        sqldb = openOrCreateDatabase("DETAILS.db", Context.MODE_PRIVATE,null);
        sqldb.execSQL("CREATE TABLE IF NOT EXISTS USERS(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,name VARCHAR,gender VARCHAR,city VARCHAR,hobbies VARCHAR)");



        txtId = (EditText) findViewById(R.id.txtId);
        txtName = (EditText) findViewById(R.id.txtNme);

        rbfemale = (RadioButton) findViewById(R.id.rbfemale);
        rbmale = (RadioButton) findViewById(R.id.rbmale);
        rgroup = (RadioGroup)findViewById(R.id.rgroup);

        spinnercity = (Spinner) findViewById(R.id.spinnercity);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercity.setAdapter(adapter);

        chkswimming = (CheckBox) findViewById(R.id.checkBox2);
        chkhiking = (CheckBox) findViewById(R.id.checkBox);

        btnadd = (Button) findViewById(R.id.btnadd);
        btnupdate = (Button) findViewById(R.id.btnupdate);
        btndelete = (Button) findViewById(R.id.btndelete);
        btnsearch = (Button) findViewById(R.id.btnsearch);
        btnviewall = (Button) findViewById(R.id.btnviewAll);

        // insert method to insert data
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initValues();
                try
                {
                    ContentValues cv = new ContentValues();
                    cv.put("name",name);
                    cv.put("gender",gender);
                    cv.put("city",city);
                    cv.put("hobbies",hobbies);

                   long rowid= sqldb.insert("USERS",null,cv);
                   if(rowid!=-1)
                   {
                       Toast.makeText(getApplicationContext(),"Successfully inserted!",Toast.LENGTH_SHORT).show();
                   }
                   else
                       {
                           Toast.makeText(getApplicationContext(),"failed to insert!",Toast.LENGTH_SHORT).show();
                       }


                }
                catch(SQLiteException exins)
                {
                    Toast.makeText(MainActivity.this, "Exception occured!", Toast.LENGTH_SHORT).show();
                    Log.e("Insertion error",exins.getMessage());

                }
            }
        });

        // update method to update data
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initValues();
                try
                {
                    ContentValues cv = new ContentValues();
                    cv.put("name",name);
                    cv.put("gender",gender);
                    cv.put("city",city);
                    cv.put("hobbies",hobbies);

                    long rowid= sqldb.update("USERS",cv,"id=?",new String[]{id});
                    if(rowid!=-1)
                    {
                        Toast.makeText(getApplicationContext(),"Successfully updated!",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"failed to update!",Toast.LENGTH_SHORT).show();
                    }

                }
                catch(SQLiteException exupd)
                {
                    Toast.makeText(MainActivity.this, "exception occured", Toast.LENGTH_SHORT).show();
                    Log.e("update error",exupd.getMessage());

                }
            }
        });

        // delete method to delete data
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initValues();
                try
                {
                   int res= sqldb.delete("USERS","id=?",new String[] {id});
                   if(res>0)
                   {
                       Toast.makeText(getApplicationContext(),"successfully deleted",Toast.LENGTH_SHORT).show();
                   }
                   else
                       {
                           Toast.makeText(getApplicationContext(),"failed to delete",Toast.LENGTH_SHORT).show();
                       }
                }
                catch(SQLException exdel)
                {
                    Toast.makeText(MainActivity.this, "Exception occured", Toast.LENGTH_SHORT).show();
                    Log.e("update error",exdel.getMessage());
                }
            }
        });

        // Query Method to select data
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initValues();
                try
                {
                    cursor= sqldb.query("users",null,"id=?",new String[]{id},null,null,null);

                    if(cursor.moveToNext())
                    {
                        id = cursor.getString(0);
                        name = cursor.getString(1);
                        gender = cursor.getString(2);
                        city = cursor.getString(3);
                        hobbies = cursor.getString(4);
                        Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();
                    }
                    if(cursor!=null && !cursor.isClosed())
                    {
                        cursor.close();
                    }

                    txtName.setText(name);
                    if(gender.equals(rbmale.getText()))
                    {
                        rbmale.setChecked(true);
                    }
                    else if(gender.equals(rbfemale.getText()))
                    {
                        rbfemale.setChecked(true);
                    }
                    spinnercity.setSelection(getIndex(spinnercity,city)); // set item corresponding to the index supplied

                    if(hobbies.contains(chkswimming.getText()))
                    {
                        chkswimming.setChecked(true);
                    }
                    if(hobbies.contains(chkhiking.getText()
                    ))
                    {
                        chkhiking.setChecked(true);
                    }

                }
                catch(SQLiteException exsel)
                {
                    Toast.makeText(MainActivity.this, "Failed to fetch", Toast.LENGTH_SHORT).show();
                    Log.e(" error",exsel.getMessage());
                }
            }
        });


        btnviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initValues();

            }
        });
    }
    // Get Spinner Index corresponding to an item
    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }

    public void initValues()
    {
        id = txtId.getText().toString();
        name = txtName.getText().toString();

        gender="";
        if(rbmale.isChecked())
        {
            gender = rbmale.getText().toString();
        }
        else if(rbfemale.isChecked())
        {
            gender = rbfemale.getText().toString();
        }

        city = spinnercity.getSelectedItem().toString();

        hobbies = "";
        if(chkswimming.isChecked())
        {
            hobbies = chkswimming.getText().toString();
        }
        if(chkhiking.isChecked())
        {
            hobbies = hobbies+" "+chkhiking.getText().toString();
        }

        //Toast.makeText(this, name+","+gender+","+city+","+hobbies, Toast.LENGTH_SHORT).show();
        txtId.setText("");
        txtName.setText("");
        rbmale.setChecked(false);
        rbfemale.setChecked(false);
        spinnercity.setSelection(0);
        chkhiking.setChecked(false);
        chkswimming.setChecked(false);
    }
}
