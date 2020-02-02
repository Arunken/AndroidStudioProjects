package com.example.silverdoe.a17_menus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

public class ActivityPopup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);


    }

    public void showPopup(View view)
    {
        PopupMenu popup = new PopupMenu(this,view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.main_menu,popup.getMenu());
        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
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
                return false;
            }
        });
    }
}
