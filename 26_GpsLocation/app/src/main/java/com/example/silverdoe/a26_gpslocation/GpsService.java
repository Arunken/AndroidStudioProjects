package com.example.silverdoe.a26_gpslocation;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.List;


public class GpsService extends Service {

    LocationManager locmanager;
    LocationListener loclistener;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        super.onCreate();

        loclistener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Intent i = new Intent("loc_update");
                i.putExtra("lat",location.getLatitude());
                i.putExtra("long",location.getLongitude());
                sendBroadcast(i);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        };

        locmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,loclistener);
/*        List<String> providersList=locmanager.getProviders(true);
        for(String provider:providersList)
        {
            Log.i("Provider : ",provider);
        }*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(locmanager!=null)
        {
            locmanager.removeUpdates(loclistener);
        }
    }
}

