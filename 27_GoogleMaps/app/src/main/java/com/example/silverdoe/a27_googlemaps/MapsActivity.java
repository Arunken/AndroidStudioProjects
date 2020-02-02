package com.example.silverdoe.a27_googlemaps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitude,longitude;
    BroadcastReceiver breceiver;
    LatLng mposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mposition = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(mposition).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mposition));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(breceiver ==null)
        {
            // Access the broadcasts from the service of 26_GpsProvider application
            breceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    latitude=Double.parseDouble(intent.getExtras().get("lat").toString());
                    longitude=Double.parseDouble(intent.getExtras().get("long").toString());

                    if(mMap!=null)
                    {
                        mMap.clear();
                    }
                    // use geocoder to set marker title as address
                    mposition = new LatLng(latitude,longitude);
                    mMap.addMarker(new MarkerOptions().position(mposition).title("My location!"));
                    //mMap.moveCamera(CameraUpdateFactory.newLatLng(mposition));
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(mposition));
                    //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mposition,12));
                }
            };
        }
        registerReceiver(breceiver,new IntentFilter("loc_update"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(breceiver!=null)
        {
            unregisterReceiver(breceiver);
        }
    }
}
