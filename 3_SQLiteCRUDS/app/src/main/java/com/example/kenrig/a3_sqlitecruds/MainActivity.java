package com.example.kenrig.a3_sqlitecruds;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
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
        txtName = (EditText) findViewById(R.id.txtName);

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

        // INSERT DATA
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initValues();
                try
                {
                    String sqliteAdd = "INSERT INTO USERS(name,gender,city,hobbies) VALUES('"+name+"','"+gender+"','"+city+"','"+hobbies+"')";
                    sqldb.execSQL(sqliteAdd);
                    Toast.makeText(getApplicationContext(),"Successfully inserted!",Toast.LENGTH_SHORT).show();
                }
                catch(SQLiteException exins)
                {
                    Toast.makeText(MainActivity.this, "Failed to Insert", Toast.LENGTH_SHORT).show();
                    Log.e("Insertion error",exins.getMessage());

                }
            }
        });

        // UPDATE DATA
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initValues();
                try
                {
                    String sqlupdate = "UPDATE USERS SET name='"+name+"',gender='"+gender+"',city='"+city+"',hobbies='"+hobbies+"' WHERE id="+id;
                    sqldb.execSQL(sqlupdate);
                    Toast.makeText(getApplicationContext(),"Successfully updated!",Toast.LENGTH_SHORT).show();
                }
                catch(SQLiteException exupd)
                {
                    Toast.makeText(MainActivity.this, "Failed to update", Toast.LENGTH_SHORT).show();
                    Log.e("update error",exupd.getMessage());

                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initValues();
                try
                {
                    String sqldelete = "DELETE FROM USERS WHERE id="+id;
                    sqldb.execSQL(sqldelete);
                }
                catch(SQLException exdel)
                {
                    Toast.makeText(MainActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show();
                    Log.e("update error",exdel.getMessage());
                }
            }
        });

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initValues();
                try
                {
                    String sqlselect = "SELECT * FROM USERS WHERE id="+id;
                    cursor = sqldb.rawQuery(sqlselect,null);

                    if(cursor.moveToNext())
                    {
                        name = cursor.getString(1);
                        gender = cursor.getString(2);
                        city = cursor.getString(3);
                        hobbies = cursor.getString(4);
                        Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT).show();
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

                    if(hobbies.equals(chkswimming.getText()))
                    {
                        chkswimming.setChecked(true);
                    }
                    else if(hobbies.equals(chkhiking.getText()))
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
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
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

    }

}
