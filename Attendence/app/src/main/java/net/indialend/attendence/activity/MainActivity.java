package net.indialend.attendence.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import net.indialend.attendence.bean.Attendence;
import net.indialend.attendence.fragment.ProfileFragment;
import net.indialend.attendence.R;
import net.indialend.attendence.operation.AttendenceOperation;

public class MainActivity extends CommonActivity
        implements LocationListener,OnMapReadyCallback {

    private GoogleMap mMap;
    Marker marker;
    private static final int ERROR_DIALOG_REQUEST = 9001;


    private Handler customHandler = new Handler();
    private long startTime = 0L;
    private long endTime = 0L;


    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    LatLng currentlatLng;

    private Button startButton;
    private TextView timerValue;

    public  void doCheckOut(){
        startButton.setText("Check In");
        endTime = SystemClock.uptimeMillis();

        timeSwapBuff += timeInMilliseconds;
        customHandler.removeCallbacks(updateTimerThread);
        timerValue.setText("0:0:0");
    }

    public void doCheckIn(){
        startButton.setText("Check Out");
        startTime = SystemClock.uptimeMillis();

        customHandler.postDelayed(updateTimerThread, 0);
    }


    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            long s = updatedTime % 60;
            long m = (updatedTime / 60) % 60;
            long h = (updatedTime / (60 * 60)) % 24;


//            int secs = (int) (updatedTime / 1000);
//            int mins = secs / 60;
//            int hour = mins / 60;
//            secs = secs % 60;
//            int milliseconds = (int) (updatedTime % 1000);
            timerValue.setText(""
                    + String.format("%02d", h) + ":"
                    + String.format("%02d", m) + ":"
                    + String.format("%03d", s));
            customHandler.postDelayed(this, 0);
        }

    };


    public void onCHKClick(View v) {
        String  label =  startButton.getText().toString();
        Log.d("MAP::" , label);
        Log.d("MAP::" , currentlatLng.toString());

        if(currentlatLng != null) {
            Attendence attendence =  new Attendence();
            attendence.setChkInLat(currentlatLng.latitude);
            attendence.setChkOutLat(currentlatLng.latitude);
            attendence.setChkInLong(currentlatLng.longitude);
            attendence.setChkOutLong(currentlatLng.longitude);
            new AttendenceOperation(this, attendence, label).execute();
        }else {
            Toast.makeText(this, "Location not fetched", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDrawer();
        timerValue = (TextView) findViewById(R.id.timerValue);

        startButton = (Button) findViewById(R.id.checkin);



        SupportMapFragment supportMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        supportMapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            return ;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                gotoLocation(location.getLatitude(), location.getLongitude());
            }
        });



        Log.d("MAP::" , "Location1");

    }




    private void gotoLocation(double latitude, double longitude) {
        currentlatLng = new LatLng(latitude, longitude);

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(currentlatLng, 15);
        mMap.moveCamera(update);


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


}
