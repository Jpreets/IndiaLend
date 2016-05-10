package net.indialend.operation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
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
 * Created by jaspreetsingh on 5/9/16.
 */
public class RestOperation extends AsyncTask<String, Void, Void> {

    private String Content;
    private String Error = null;
    private ProgressDialog Dialog;
    String data ="";
    String postData ="" ;
    Activity activity;

    public RestOperation(Activity activity, String postData){
        this.activity = activity;
        Dialog  = new ProgressDialog(activity);

    }

    @Override
    protected void onPreExecute() {

        Dialog.setMessage("Please wait..");
        Dialog.show();

        try{
            // Set Request parameter
            data +="&data="+ URLEncoder.encode(postData,"UTF-8");

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    protected void onPostExecute(Void unused) {
        // NOTE: You can call UI Element here.

        // Close progress dialog
        Dialog.dismiss();

        if (Error != null) {

            Toast.makeText(activity, "Output : "+Error , Toast.LENGTH_SHORT );


        } else {

            Toast.makeText(activity, "Output : "+Content , Toast.LENGTH_SHORT );

            // Show Response Json On Screen (activity)

            /****************** Start Parse Response JSON Data *************/

//            String OutputData = "";
//            JSONObject jsonResponse;
//
//            try {
//
//                /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
//                jsonResponse = new JSONObject(Content);
//
//                /***** Returns the value mapped by name if it exists and is a JSONArray. ***/
//                /*******  Returns null otherwise.  *******/
//                JSONArray jsonMainNode = jsonResponse.optJSONArray("Android");
//
//                /*********** Process each JSON Node ************/
//
//                int lengthJsonArr = jsonMainNode.length();
//
//                for(int i=0; i < lengthJsonArr; i++)
//                {
//                    /****** Get Object for each JSON node.***********/
//                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
//
//                    /******* Fetch node values **********/
//                    String name       = jsonChildNode.optString("name").toString();
//                    String number     = jsonChildNode.optString("number").toString();
//                    String date_added = jsonChildNode.optString("date_added").toString();
//
//
//                }
//                /****************** End Parse Response JSON Data *************/
//
//                //Show Parsed Output on screen (activity)
//
//
//            } catch (JSONException e) {
//
//                e.printStackTrace();
//            }


        }
    }



    @Override
    protected Void doInBackground(String... urls) {
        BufferedReader reader=null;

        // Send data
        try
        {

            // Defined URL  where to send data
            URL url = new URL(urls[0]);

            // Send POST data request

            URLConnection urlc = url.openConnection();
            HttpURLConnection conn  = (HttpURLConnection) urlc;
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

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
            Content = sb.toString();
        }
        catch(Exception ex)
        {
            Error = ex.getMessage();
        }
        finally
        {
            try
            {

                reader.close();
            }

            catch(Exception ex) {}
        }

        /*****************************************************/
        return null;
    }

}
