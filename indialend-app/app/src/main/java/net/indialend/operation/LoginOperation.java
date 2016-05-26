package net.indialend.operation;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.indialend.R;
import net.indialend.activity.CurrentLocation;
import net.indialend.bean.User;
import net.indialend.dao.DatabaseHandler;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by jaspreetsingh on 5/23/16.
 */
public class LoginOperation extends AsyncTask<String, Void, Void> {
    User user ;
    Activity activity;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;

   public LoginOperation(Activity activity,User user){
       this.activity =activity;
      this.user=user;
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

        if(user.getUserId() ==0) {

            Toast.makeText(activity, "Invalid Credentials", Toast.LENGTH_SHORT);
            return;
        }
        DatabaseHandler db = new DatabaseHandler(activity);

        db.addUser(user);



        Intent mapActivityIntent = new Intent(activity, CurrentLocation.class);
        activity.startActivity(mapActivityIntent);
        activity.finish();

    }


    @Override
    protected Void doInBackground(String... urls) {
        BufferedReader reader=null;

        // Send data
        try
        {
            // Defined URL  where to send data
            URL url = new URL("http://jazzkart-jazzkart.rhcloud.com/indialend/signIn");

            // Send POST data request

            URLConnection urlc = url.openConnection();
            HttpURLConnection conn  = (HttpURLConnection) urlc;
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String data = this.user.getParamData();
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
            long id =  jsonObject.getLong("user_id");
            if(id != 0){
                user.setUserId(id);
                user.setName(jsonObject.getString("name"));
                user.setPassword(jsonObject.getString("password"));
                user.setGender(jsonObject.getString("gender"));
                user.setEmail(jsonObject.getString("email"));
                user.setPhone(jsonObject.getString("phone"));
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
