package net.indialend.attendence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.indialend.attendence.R;
import net.indialend.attendence.bean.User;
import net.indialend.attendence.dao.DatabaseHandler;
import net.indialend.attendence.operation.LoginOperation;

import org.json.JSONException;

/**
 * Created by jaspreetsingh on 5/23/16.
 */
public class LoginActivity extends AppCompatActivity {

    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//
//        Intent i=new Intent("any string");
//        i.setClass(this, BootUp.class);
//        this.sendBroadcast(i);

        User user =  db.getUser();
        Log.d("ERROR" , "User : "+user);
        if(user != null){
            Intent mapActivityIntent = new Intent(this, MainActivity.class);
            startActivity(mapActivityIntent);
        }

        setContentView(R.layout.activity_login);
    }



    public void signIn(View v) throws JSONException {

        String staffId = ((EditText) findViewById(R.id.staffId) ).getText().toString();

        String password =((EditText) findViewById(R.id.password)).getText().toString();


        if(staffId == null  ||  staffId.trim().isEmpty()
                || password == null ||password.trim().isEmpty()){
            Toast.makeText(this, "Every field is mandatory" , Toast.LENGTH_SHORT );
            return;

        }

        User user =  new User();
        user.setPassword(password);
        user.setStaffId(staffId);


        new LoginOperation(this,user).execute();

    }
}
