package net.indialend.attendence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import net.indialend.attendence.R;

/**
 * Created by jaspreetsingh on 7/29/16.
 */
public class FoodActivity extends CommonActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        setupDrawer();

    }

    public void scanQRCode(View v){
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        startActivityForResult(intent, 0);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                // Handle successful scan

            } else if (resultCode == RESULT_CANCELED) {
                //Handle cancel
            }
        }
    }
}
