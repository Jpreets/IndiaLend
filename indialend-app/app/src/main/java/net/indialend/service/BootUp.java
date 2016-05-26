package net.indialend.service;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.Timer;
import java.util.TimerTask;

public class BootUp extends BroadcastReceiver {

    public BootUp() {
    }

    private static final String TAG = "myTag:";

    GoogleApiClient mGoogleApiClient;

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent startServiceIntent = new Intent(context, MyService.class);
        context.startService(startServiceIntent);

    }

}
