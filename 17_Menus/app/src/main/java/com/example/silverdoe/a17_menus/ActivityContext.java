package com.example.silverdoe.a17_menus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityContext extends AppCompatActivity {

    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);

        list = (ListView) findViewById(R.id.list1);
        String[] arr = {"User1","User2","User3","User4","User5","User6","User7"};

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arr);
        list.setAdapter(adapter);
        registerForContextMenu(list);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE,1,Menu.NONE,"View");
        menu.add(Menu.NONE,2,Menu.NONE,"Select");
        menu.add(Menu.NONE,3,Menu.NONE,"Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo cminfo =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String listsel = list.getAdapter().getItem(cminfo.position).toString();
        Toast.makeText(this,listsel+" option : "+item.getTitle(),Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
}
