package net.indialend.attendence.operation;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;

import net.indialend.attendence.Constant;
import net.indialend.attendence.R;
import net.indialend.attendence.activity.LeaveActivity;
import net.indialend.attendence.bean.Leave;
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
public class AddLeaveOperation extends AsyncTask<String, Void, Void> {
    LeaveActivity activity;
    Leave leave;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;
    DatabaseHandler db ;
    User user;

    public AddLeaveOperation(LeaveActivity activity, Leave leave){
        this.activity =activity;
        this.leave= leave;
        db = new DatabaseHandler(activity);
        user =  db.getUser();
        this.leave= leave;
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

        activity.clearFields();

        new LeaveDetailOperation(activity).execute();


    }


    @Override
    protected Void doInBackground(String... urls) {
        BufferedReader reader=null;

        // Send data
        try
        {
            // Defined URL  where to send data
            URL url = new URL(Constant.leaveSaveUrl);

            // Send POST data request

            URLConnection urlc = url.openConnection();
            HttpURLConnection conn  = (HttpURLConnection) urlc;
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String data = "staffId="+user.getStaffId()+leave.getParamData();
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
