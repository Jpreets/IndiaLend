package net.indialend.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import net.indialend.R;
import net.indialend.operation.RestOperation;

public class CurrentLocation extends AppCompatActivity implements LocationListener {

    private GoogleMap mMap;
    Marker marker;
    private static final int ERROR_DIALOG_REQUEST = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);
        Log.d("ERROR" , "Location");

        if (serviceOK()) {

            SupportMapFragment supportMapFragment =
                    (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mMap = supportMapFragment.getMap();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mMap.setMyLocationEnabled(true);

            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            Toast.makeText(this, "isGPSEnabled :"+isGPSEnabled, Toast.LENGTH_SHORT).show();

            if(isGPSEnabled) {
                Criteria criteria = new Criteria();
                String bestProvider = locationManager.getBestProvider(criteria, true);
                Location location = locationManager.getLastKnownLocation(bestProvider);
                if (location != null) {
                    onLocationChanged(location);
                }

                locationManager.requestLocationUpdates(bestProvider, 20000, 0, this);
                Toast.makeText(this, "Ready to test", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void gotoLocation(double latitude,double longitude){
        LatLng latLng = new LatLng(latitude, longitude);

        if(marker != null){
            marker.remove();
        }
        marker= mMap.addMarker(new MarkerOptions().position(latLng));

        CameraUpdate update  = CameraUpdateFactory.newLatLngZoom(latLng,15);
        mMap.moveCamera(update);


        Toast.makeText(this, "Latitude:" + latitude + ", Longitude:" + longitude , Toast.LENGTH_SHORT );

        String serverURL = "http://google.com/media/webservice/JsonReturn.php";

        // Use AsyncTask execute Method To Prevent ANR Problem
        new RestOperation(this,"test").execute(serverURL);
    }


    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        gotoLocation(latitude, longitude);

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

    public boolean serviceOK(){
        int isAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if(isAvailable == ConnectionResult.SUCCESS){
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(isAvailable)){
//            Dialog dialog =  GoogleApiAvailability.getInstance().getErrorDialog(this, isAvailable, ERROR_DIALOG_REQUEST);
//            dialog.show();
            Toast.makeText(this, "isUserResolvableError" , Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Can't connect" , Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
