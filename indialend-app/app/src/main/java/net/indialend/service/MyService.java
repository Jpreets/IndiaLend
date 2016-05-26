package net.indialend.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import net.indialend.bean.User;
import net.indialend.dao.DatabaseHandler;
import net.indialend.operation.RestOperation;

public class MyService extends Service
{
    public MyService() {
    }


    LocationListener mLocationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location mLastLocation) {
            Log.v("SS:", "LAT:" + mLastLocation.getLatitude() + " Long :" + mLastLocation.getLongitude());
            User user =  db.getUser();
            if(user != null) {
                Log.v("SS:", user.toString());

                user.setLatitute(mLastLocation.getLatitude());
                user.setLongitute(mLastLocation.getLongitude());
                user.setService(true);


                // Use AsyncTask execute Method To Prevent ANR Problem
                new RestOperation(user).execute("");
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
    LocationManager mLocationManager;
    //    GoogleApiClient mGoogleApiClient;
    Context context;
    DatabaseHandler db;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        context = getApplicationContext();
        db = new DatabaseHandler(context);
        Log.v("SS:" ,"START");

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return 0 ;
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,
                50, mLocationListener);
//
//        mGoogleApiClient = new GoogleApiClient.Builder(context)
//                .addApi(LocationServices.API)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .build();
//
//        Log.v("SS:" ,"START");
//
//        new Thread(new Runnable() {
//            public void run() {
//
//                Log.v("SS:" ,"Connect");
//
////                    mGoogleApiClient.reconnect();
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                run();
//            }
//        }).start();
        return Service.START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return  null;
    }

//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//
//
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            return;
//        }
//        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//        if(mLastLocation != null) {
//
//            Log.v("SS:", "LAT:" + mLastLocation.getLatitude() + " Long :" + mLastLocation.getLongitude());
//            User user =  db.getUser();
//            if(user != null) {
//                user.setLatitute(mLastLocation.getLatitude());
//                user.setLongitute(mLastLocation.getLongitude());
//
//
//                // Use AsyncTask execute Method To Prevent ANR Problem
//                new RestOperation(user).execute("");
//            }
//        }
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
}
