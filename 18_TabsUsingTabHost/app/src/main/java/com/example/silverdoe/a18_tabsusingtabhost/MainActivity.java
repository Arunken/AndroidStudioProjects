package com.example.silverdoe.a18_tabsusingtabhost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost host = (TabHost) findViewById(R.id.tabhost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("Home");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Tab 1");
        host.addTab(spec);

        spec = host.newTabSpec("Calls");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Tab 2");
        host.addTab(spec);

        spec = host.newTabSpec("Text");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Tab 3");
        host.addTab(spec);

        host.setCurrentTab(0);
        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(MainActivity.this, tabId, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
