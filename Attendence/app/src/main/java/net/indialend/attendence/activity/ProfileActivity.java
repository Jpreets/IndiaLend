package net.indialend.attendence.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;

import net.indialend.attendence.R;

/**
 * Created by jaspreetsingh on 7/6/16.
 */
public class ProfileActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupDrawer();

    }
}
