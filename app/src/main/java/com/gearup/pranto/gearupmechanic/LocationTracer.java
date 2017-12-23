package com.gearup.pranto.gearupmechanic;

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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static android.content.Intent.getIntent;
import static com.gearup.pranto.gearupmechanic.AppController.TAG;

/**
 * Created by pranto on 12/3/17.
 */

public class LocationTracer extends Service {

    LocationManager my_manager;
    LocationListener my_listener;
    double lat, longd;
    String phone, status;
    String url = Links.UPDATE_LOCATION;

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        super.onCreate();
        status = "true";

        my_listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat = location.getLatitude();
                longd = location.getLongitude();
                updateLocation(lat,longd, status);

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };

        my_manager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        my_manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000,10,my_listener);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (my_manager != null)
        {
            my_manager.removeUpdates(my_listener);
        }
        status = "false";
        updateLocation(lat,longd,status);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        phone = intent.getStringExtra("phone");
        return START_STICKY;
    }


    private void updateLocation(double lat, double lon, String stat)
    {
        final String latt = Double.toString(lat);
        final String lonn = Double.toString(lon);
        final String status = stat;
        StringRequest rq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                  Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parr = new HashMap<String, String>();
                parr.put("phone", phone);
                parr.put("lat", latt);
                parr.put("lon",lonn);
                parr.put("status", status);
                return parr;
            }
        };

       AppController.getInstance(getApplicationContext()).addToRequestQueue(rq);
    }
}
