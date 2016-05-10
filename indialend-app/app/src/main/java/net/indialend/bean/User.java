package net.indialend.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jaspreetsingh on 5/10/16.
 */
public class User {
    private String phone;
    private String email;
    private String name;
    private String gender;

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
}
