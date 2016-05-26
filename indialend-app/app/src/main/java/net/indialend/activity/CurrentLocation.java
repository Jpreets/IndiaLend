package net.indialend.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import net.indialend.R;
import net.indialend.bean.User;
import net.indialend.dao.DatabaseHandler;
import net.indialend.operation.RestOperation;


public class CurrentLocation extends AppCompatActivity implements LocationListener {

    private GoogleMap mMap;
    Marker marker;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    DatabaseHandler db = new DatabaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);
        Log.d("ERROR" , "Location");
        User user =  db.getUser();
        if(user == null) {
            Intent mapActivityIntent = new Intent(this, SignIn.class);
            startActivity(mapActivityIntent);
            finish();
        }


        if (serviceOK()) {

            SupportMapFragment supportMapFragment =
                    (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mMap =  supportMapFragment.getMap();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {
                    gotoLocation(location.getLatitude(), location.getLongitude());
                }
            });

        }
    }


    private void gotoLocation(double latitude,double longitude){
        LatLng latLng = new LatLng(latitude, longitude);

        CameraUpdate update  = CameraUpdateFactory.newLatLngZoom(latLng,15);
        mMap.moveCamera(update);


        Toast.makeText(this, "Latitude:" + latitude + ", Longitude:" + longitude , Toast.LENGTH_SHORT );


        User user =  db.getUser();
        user.setLatitute(latitude);
        user.setLongitute(longitude);


        // Use AsyncTask execute Method To Prevent ANR Problem
        new RestOperation(user).execute("");
    }


    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        gotoLocation(latitude, longitude);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_items, menu);//Menu Resource, Menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                User u  = db.getUser();
                u.setActive(false);
                new RestOperation(u).execute("");

                db.deleteUser();
                Intent mapActivityIntent = new Intent(this, SignIn.class);
                startActivity(mapActivityIntent);
                finish();
                Toast.makeText(getApplicationContext(),"Logout Successfull",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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

    public boolean serviceOK(){
        int isAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if(isAvailable == ConnectionResult.SUCCESS){
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(isAvailable)){

            Toast.makeText(this, "isUserResolvableError" , Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Can't connect" , Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
