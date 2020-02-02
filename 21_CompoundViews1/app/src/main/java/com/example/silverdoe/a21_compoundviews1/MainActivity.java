package com.example.silverdoe.a21_compoundviews1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SideSpinner sspinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sspinner = (SideSpinner)this
                .findViewById(R.id.sidespinner_fruits);

        CharSequence fruitList[] = { "Apple",
                "Orange",
                "Pear",
                "Grapes" };
        sspinner.setValues(fruitList);
        sspinner.setSelectedIndex(1);


    }

    public void getvalue(View view)
    {
        Toast.makeText(getApplicationContext(),sspinner.getSelectedValue().toString(),Toast.LENGTH_SHORT).show();
    }
}
