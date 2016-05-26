package net.indialend.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.indialend.R;
import net.indialend.bean.User;
import net.indialend.dao.DatabaseHandler;
import net.indialend.operation.LoginOperation;
import net.indialend.operation.RestOperation;
import net.indialend.service.BootUp;

import org.json.JSONException;

/**
 * Created by jaspreetsingh on 5/23/16.
 */
public class SignIn extends AppCompatActivity {

    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent i=new Intent("any string");
        i.setClass(this, BootUp.class);
        this.sendBroadcast(i);

        User user =  db.getUser();
        Log.d("ERROR" , "User : "+user);
        if(user != null){
            Intent mapActivityIntent = new Intent(this, CurrentLocation.class);
            startActivity(mapActivityIntent);
        }

        setContentView(R.layout.activity_sign_in);
    }

    public void gotoSignUp(View v) throws JSONException {
        Intent mapActivityIntent = new Intent(this, SignUp.class);
        startActivity(mapActivityIntent);
        finish();
    }

    public void signIn(View v) throws JSONException {

        String phoneEmail = ((EditText) findViewById(R.id.phoneEmail) ).getText().toString();

        String password =((EditText) findViewById(R.id.password)).getText().toString();


        if(phoneEmail == null  ||  phoneEmail.trim().isEmpty()
                || password == null ||password.trim().isEmpty()){
            Toast.makeText(this, "Every field is mandatory" , Toast.LENGTH_SHORT );
            return;

        }

        User user =  new User();
        user.setPhone(phoneEmail);
        user.setPassword(password);


         new LoginOperation(this,user).execute();

    }
}
