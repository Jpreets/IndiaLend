package net.indialend.attendence.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import net.indialend.attendence.R;
import net.indialend.attendence.operation.AttendenceOperation;


public class MapsFragment extends  android.support.v4.app.Fragment
        implements LocationListener,OnMapReadyCallback, View.OnClickListener {


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
//    DatabaseHandler db = new DatabaseHandler(this);

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timerValue.setText("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }

    };


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
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

    public  void doCheckOut(){
        startButton.setText("Check In");
        endTime = SystemClock.uptimeMillis();

        timeSwapBuff += timeInMilliseconds;
        customHandler.removeCallbacks(updateTimerThread);
    }

    public void doCheckIn(){
        startButton.setText("Check Out");
        startTime = SystemClock.uptimeMillis();

        customHandler.postDelayed(updateTimerThread, 0);
    }


    public void onClick(View view){
        String  label =  startButton.getText().toString();
        if(currentlatLng != null) {
            new AttendenceOperation(this, null, label);
        }else {
            Toast.makeText(this.getActivity(), "Location not fetched", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        timerValue = (TextView) rootView.findViewById(R.id.timerValue);

        startButton = (Button) rootView.findViewById(R.id.checkin);

        startButton.setOnClickListener(this);
//            public void onClick(View view) {
//
//                String  label =  startButton.getText().toString();
//                new AttendenceOperation(this,null,"checkin");
//
//                if("Check In".equals(label)){
//                    startButton.setText("Check Out");
//                    startTime = SystemClock.uptimeMillis();
//
//                    customHandler.postDelayed(updateTimerThread, 0);
//                    new AttendenceOperation(getActivity(),null,"checkin");
//                }
//
//                if("Check Out".equals(label)){
//                    startButton.setText("Check In");
//                    endTime = SystemClock.uptimeMillis();
//
//                    timeSwapBuff += timeInMilliseconds;
//                    customHandler.removeCallbacks(updateTimerThread);
//                    new AttendenceOperation(getActivity(),null,"checkout");
//                }
//
//            }
//        });



        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();

        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


//    @Override
//    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        setContentView(R.layout.fragment_maps);
//        SupportMapFragment supportMapFragment =
//                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//
//        supportMapFragment.getMapAsync(this);
//        Log.d("MAP::" , "Location");
//
//    }

    private void gotoLocation(double latitude, double longitude) {
         currentlatLng = new LatLng(latitude, longitude);

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(currentlatLng, 15);
        mMap.moveCamera(update);


//        Toast.makeText(this, "Latitude:" + latitude + ", Longitude:" + longitude, Toast.LENGTH_SHORT);

//
//        User user = db.getUser();
//        user.setLatitute(latitude);
//        user.setLongitute(longitude);
//
//
//        // Use AsyncTask execute Method To Prevent ANR Problem
//        new RestOperation(user).execute("");
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

    public boolean serviceOK() {
        int isAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getActivity());
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(isAvailable)) {

//            Toast.makeText(this, "isUserResolvableError", Toast.LENGTH_SHORT).show();

        } else {
//            Toast.makeText(this, "Can't connect", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


}