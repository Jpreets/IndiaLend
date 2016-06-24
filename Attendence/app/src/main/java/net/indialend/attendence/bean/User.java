package net.indialend.attendence.bean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by jaspreetsingh on 5/10/16.
 */
public class User {
    private long userId;
    private String phone;
    private String email;
    private String name;
    private String gender;
    private double latitute;
    private double longitute;
    private String password;
    private boolean service;
    private String gcmToken;
    private boolean active = true;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getGcmToken() {
        return gcmToken;
    }

    public void setGcmToken(String gcmToken) {
        this.gcmToken = gcmToken;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isService() {
        return service;
    }

    public void setService(boolean service) {
        this.service = service;
    }

    public double getLatitute() {
        return latitute;
    }

    public void setLatitute(double latitute) {
        this.latitute = latitute;
    }

    public double getLongitute() {
        return longitute;
    }

    public void setLongitute(double longitute) {
        this.longitute = longitute;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        JSONObject jsonObject =  new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("email", email);
            jsonObject.put("phone", phone);
            jsonObject.put("gender", gender);
        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    public String getParamData(){

        String param = "";
        try {
            param+="&name="+ URLEncoder.encode(this.getName() ==null ? "":this.getName(),"UTF-8");
            param+="&phone="+ URLEncoder.encode(this.getPhone() ==null ? "":this.getPhone(),"UTF-8");
            param+="&email="+ URLEncoder.encode(this.getEmail() ==null ? "":this.getEmail(),"UTF-8");
            param+="&gender="+ URLEncoder.encode(this.getGender() ==null ? "":this.getGender(),"UTF-8");
            param+="&password="+ URLEncoder.encode(this.getPassword() ==null ? "":this.getPassword(),"UTF-8");
            param+="&latitude="+ URLEncoder.encode(Double.toString(this.getLatitute()),"UTF-8");
            param+="&longitute="+ URLEncoder.encode(Double.toString(this.getLongitute()),"UTF-8");
            param+="&service="+ URLEncoder.encode(Boolean.toString(this.isService()),"UTF-8");
            param+="&active="+ URLEncoder.encode(Boolean.toString(this.isActive()),"UTF-8");
            param+="&gcmToken="+ URLEncoder.encode(this.getGcmToken() ==null ?"":this.getGcmToken(),"UTF-8");


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return param;
    }
}
