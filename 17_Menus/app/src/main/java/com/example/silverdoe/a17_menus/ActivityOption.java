package com.example.silverdoe.a17_menus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class ActivityOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(getApplicationContext());
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save :
                Toast.makeText(getApplicationContext(),"Save",Toast.LENGTH_SHORT).show();
                break;
            case R.id.cut :
                Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.copy :
                Toast.makeText(getApplicationContext(),"Copy",Toast.LENGTH_SHORT).show();
                break;
            case R.id.paste :
                Toast.makeText(getApplicationContext(),"Paste",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete :
                Toast.makeText(getApplicationContext(),"Delete",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
