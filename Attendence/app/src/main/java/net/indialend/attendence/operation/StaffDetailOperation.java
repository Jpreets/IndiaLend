package net.indialend.attendence.operation;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.google.gson.Gson;

import net.indialend.attendence.Constant;
import net.indialend.attendence.R;
import net.indialend.attendence.activity.MainActivity;
import net.indialend.attendence.activity.ProfileActivity;
import net.indialend.attendence.bean.Attendence;
import net.indialend.attendence.bean.Staff;
import net.indialend.attendence.bean.User;
import net.indialend.attendence.dao.DatabaseHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by jaspreetsingh on 5/23/16.
 */
public class StaffDetailOperation extends AsyncTask<String, Void, Void> {
    ProfileActivity activity;
    Staff staff;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;
    DatabaseHandler db ;
    User user;

    public StaffDetailOperation(ProfileActivity activity){
        this.activity =activity;
        db = new DatabaseHandler(activity);
        user =  db.getUser();
    }


    @Override
    protected void onPreExecute() {
        FrameLayout progressBarHolder = (FrameLayout)activity.findViewById(R.id.progressBarHolder);
        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(200);
        progressBarHolder.setAnimation(inAnimation);
        progressBarHolder.setVisibility(View.VISIBLE);


    }

    @Override
    protected void onPostExecute(Void unused) {
        // NOTE: You can call UI Element here.
        FrameLayout progressBarHolder = (FrameLayout)activity.findViewById(R.id.progressBarHolder);
        outAnimation = new AlphaAnimation(1f, 0f);
        outAnimation.setDuration(200);
        progressBarHolder.setAnimation(outAnimation);
        progressBarHolder.setVisibility(View.GONE);

        if(staff == null) {
            return;
        }

        ((EditText) activity.findViewById(R.id.name)).setText(staff.getName());
        ((EditText) activity.findViewById(R.id.email)).setText(staff.getEmail());
        ((EditText) activity.findViewById(R.id.phone)).setText(staff.getPhone());
        ((EditText) activity.findViewById(R.id.currentAddr)).setText(staff.getCurrentAddr());
        ((EditText) activity.findViewById(R.id.permanentAddr)).setText(staff.getPermanentAddr());
        ((EditText) activity.findViewById(R.id.aadhaarNo)).setText(staff.getAadhaarNo());
        ((EditText) activity.findViewById(R.id.panNo)).setText(staff.getPanNo());
        ((EditText) activity.findViewById(R.id.fatherName)).setText(staff.getFatherName());
        ((EditText) activity.findViewById(R.id.motherName)).setText(staff.getMotherName());
        ((EditText) activity.findViewById(R.id.bloodGroup)).setText(staff.getBloodGroup());

        if("FEMALE".equals(staff.getGender()) ){
            ((RadioButton) activity.findViewById(R.id.male)).setChecked(false);
            ((RadioButton) activity.findViewById(R.id.female)).setChecked(true);
        }else{
            ((RadioButton) activity.findViewById(R.id.male)).setChecked(true);
            ((RadioButton) activity.findViewById(R.id.female)).setChecked(false);

        }


    }


    @Override
    protected Void doInBackground(String... urls) {
        BufferedReader reader=null;

        // Send data
        try
        {
            // Defined URL  where to send data
            URL url = new URL(Constant.staffDetailUrl);

            // Send POST data request

            URLConnection urlc = url.openConnection();
            HttpURLConnection conn  = (HttpURLConnection) urlc;
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String data = "staffId="+user.getStaffId();
            Log.d("OUTPUT:",data);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "");
            }

            // Append Server Response To Content String
            String Content = sb.toString();
            Log.v("OUTPUT:",Content);

             staff = new Gson().fromJson(Content, Staff.class);

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            Log.d("OUTPUT:",ex.getMessage());

        }
        finally
        {
            try{reader.close();} catch(Exception ex) {}
        }
        return null;

    }
}
