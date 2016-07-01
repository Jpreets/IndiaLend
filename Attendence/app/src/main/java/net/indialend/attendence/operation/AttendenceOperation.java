package net.indialend.attendence.operation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.Toast;

import net.indialend.attendence.R;
import net.indialend.attendence.activity.MainActivity;
import net.indialend.attendence.bean.Attendence;
import net.indialend.attendence.bean.User;
import net.indialend.attendence.dao.DatabaseHandler;
import net.indialend.attendence.fragment.MapsFragment;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by jaspreetsingh on 5/23/16.
 */
public class AttendenceOperation extends AsyncTask<String, Void, Void> {
    Attendence attendence ;
    String mode;
    Activity activity;
    MapsFragment fragment;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;

   public AttendenceOperation(MapsFragment fragment, Attendence attendence, String mode){
       this.activity =fragment.getActivity();
       this.fragment =  fragment;
      this.attendence=attendence;
       this.mode =mode;
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

        if(attendence.getAttendenceId() == 0) {

            Toast.makeText(activity, "Some network issue", Toast.LENGTH_SHORT);
            return;
        }

        if(mode.equals("Check In")){
            DatabaseHandler db = new DatabaseHandler(activity);

            User user =  db.getUser();
            user.setAttendenceId(Long.toString(attendence.getAttendenceId()));
            db.addUser(user);
            fragment.doCheckIn();
        }else{
            fragment.doCheckOut();
        }


    }


    @Override
    protected Void doInBackground(String... urls) {
        BufferedReader reader=null;

        // Send data
        try
        {
            // Defined URL  where to send data
            URL url = new URL("http://jazzkart-jazzkart.rhcloud.com/attendence-backend/staff/attendence");

            // Send POST data request

            URLConnection urlc = url.openConnection();
            HttpURLConnection conn  = (HttpURLConnection) urlc;
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String data = this.attendence.getParamData();
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
            Log.v("OUTPU:",Content);

            JSONObject jsonObject =  new JSONObject(Content);
            long id =  jsonObject.getLong("attendenceId");
            if(id != 0){

                attendence.setAttendenceId(id);
            }
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
