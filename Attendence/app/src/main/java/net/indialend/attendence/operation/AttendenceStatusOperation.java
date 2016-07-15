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

import com.google.gson.Gson;

import net.indialend.attendence.Constant;
import net.indialend.attendence.R;
import net.indialend.attendence.activity.MainActivity;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaspreetsingh on 5/23/16.
 */
public class AttendenceStatusOperation extends AsyncTask<String, Void, Void> {
    MainActivity activity;
    Attendence attendence;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;
    DatabaseHandler db ;
    User user;

    public AttendenceStatusOperation(MainActivity activity){
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

        if(attendence == null) {
            return;
        }

        Log.v("OUTPUT++++++:",""+attendence.getCheckOut());


        if(attendence.getCheckOut() == 0 ){
            user.setAttendenceId(Long.toString(attendence.getAttendenceId()));
            db.addUser(user);
            activity.doCheckIn(attendence.getCheckIn());
        }


    }


    @Override
    protected Void doInBackground(String... urls) {
        BufferedReader reader=null;

        // Send data
        try
        {
            // Defined URL  where to send data
            URL url = new URL(Constant.attendenceStatusUrl);

            // Send POST data request

            URLConnection urlc = url.openConnection();
            HttpURLConnection conn  = (HttpURLConnection) urlc;
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String data = "type=DAY&staffId="+user.getStaffId();
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

//            JSONObject jsonObject =  new JSONObject(Content);
            Attendence [] attendenceList = new Gson().fromJson(Content, Attendence[].class );
            if(attendenceList.length >0){

                attendence = attendenceList[attendenceList.length-1];
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
