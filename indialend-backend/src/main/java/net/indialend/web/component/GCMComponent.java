/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.web.component;

import com.fasterxml.jackson.databind.util.JSONPObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import net.indialend.web.model.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author jaspreetsingh
 */
@Component
public class GCMComponent {

    String serviceUrl = "https://android.googleapis.com/gcm/send";
    String API_KEY = "AIzaSyB8BG7SgkB2nDf-3jjwpsnlrtFdY1SjrO8";

    public void setMessage(User user, String deactivate){

        try {
            URL obj = new URL(serviceUrl);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "key=" + API_KEY);

            String urlParameters = "{"
                    + "   \"data\": {"
                    + "     \"deactivate\": \"" + deactivate + "\","
                    + "   },"
                    + "   \"to\": \"" + user.getGcmToken() + "\""
                    + " }";

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(urlParameters.getBytes("UTF-8"));
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + serviceUrl);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
