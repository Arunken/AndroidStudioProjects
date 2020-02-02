package com.example.silverdoe.a28_geocoder;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tvaddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvaddress = (TextView) findViewById(R.id.tvAddress);
        reverseGeocode(28.7041,77.1025);


    }

    public void reverseGeocode(double latitude,double longitude)
    {
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        if(Geocoder.isPresent())
        {
            Toast.makeText(getApplicationContext(),"Geocoder Present",Toast.LENGTH_SHORT).show();
        }
        else
            {
                Toast.makeText(getApplicationContext(),"Geocoder Not Present",Toast.LENGTH_SHORT).show();
            }


        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 5);

            if (addresses.size() > 0) {
                //Address fetchedAddress = addresses.get(0);
                StringBuilder strAddress = new StringBuilder();
                for(Address fetchedAddress:addresses) {

                    outerloop:
                    for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
                        if(!fetchedAddress.getAddressLine(i).trim().equals(""))
                        {
                            Toast.makeText(getApplicationContext(),"address found",Toast.LENGTH_SHORT).show();
                            strAddress.append(fetchedAddress.getAddressLine(i)).append(" ");
                            break outerloop;
                        }
                    }
                }
                tvaddress.setText(strAddress.toString());
                Toast.makeText(getApplicationContext(),strAddress,Toast.LENGTH_SHORT).show();

            } else {
                tvaddress.setText("Searching Current Address");
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.i("Exception Geocoder : ",e.getMessage());
        }
    }
}
