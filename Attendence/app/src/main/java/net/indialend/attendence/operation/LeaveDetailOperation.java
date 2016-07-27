package net.indialend.attendence.operation;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;

import com.google.gson.Gson;

import net.indialend.attendence.Constant;
import net.indialend.attendence.R;
import net.indialend.attendence.activity.LeaveActivity;
import net.indialend.attendence.activity.MainActivity;
import net.indialend.attendence.bean.Attendence;
import net.indialend.attendence.bean.Leave;
import net.indialend.attendence.bean.User;
import net.indialend.attendence.dao.DatabaseHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jaspreetsingh on 5/23/16.
 */
public class LeaveDetailOperation extends AsyncTask<String, Void, Void> {
    LeaveActivity activity;

    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;
    DatabaseHandler db ;
    User user;

    List<Leave> leaveList ;

    public LeaveDetailOperation(LeaveActivity activity){
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

        if(leaveList == null) {
            return;
        }

        Log.v("OUTPUT++++++:",""+leaveList);

        activity.buildTable(leaveList);



    }


    @Override
    protected Void doInBackground(String... urls) {
        BufferedReader reader=null;

        // Send data
        try
        {
            // Defined URL  where to send data
            URL url = new URL(Constant.leaveDataUrl);

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

            Leave[] leaves = new Gson().fromJson(Content, Leave[].class );
            if(leaves.length>0){
                leaveList = Arrays.asList(leaves);
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
