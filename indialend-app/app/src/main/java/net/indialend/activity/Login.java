package net.indialend.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import net.indialend.R;
import net.indialend.bean.User;
import net.indialend.dao.DatabaseHandler;
import net.indialend.operation.RestOperation;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jaspreetsingh on 5/10/16.
 */
public class Login extends AppCompatActivity {

    String gender= "";
    DatabaseHandler db = new DatabaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        User user =  db.getUser();
        Log.d("ERROR" , "User : "+user);
        if(user != null){
            Intent mapActivityIntent = new Intent(this, CurrentLocation.class);
            startActivity(mapActivityIntent);
        }

        setContentView(R.layout.activtiy_login_form);
        Log.d("ERROR" , "Login");
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.male:
                if (checked)
                    // Pirates are the best
                    gender= "MALE";
                    break;
            case R.id.female:
                if (checked)
                    // Ninjas rule
                    gender= "FEMALE";
                    break;
        }
    }

    public void signUp(View v) throws JSONException {

        EditText name  = (EditText) findViewById(R.id.name);
        String nameStr =name.getText().toString();

        EditText email  = (EditText) findViewById(R.id.email);
        String emailStr =email.getText().toString();

        EditText phone  = (EditText) findViewById(R.id.phone);
        String phoneStr =phone.getText().toString();

        if(nameStr == null  ||  nameStr.trim().isEmpty()
                || emailStr == null ||emailStr.trim().isEmpty()
                || phoneStr == null || phoneStr.trim().isEmpty()){
            Toast.makeText(this, "Every field is mandatory" , Toast.LENGTH_SHORT );
            return;

        }

        User user =  new User();
        user.setName(nameStr);
        user.setEmail(emailStr);
        user.setPhone(phoneStr);
        user.setGender(gender);

        db.addUser(user);

        new RestOperation(this,user).execute("");

        Intent mapActivityIntent = new Intent(this, CurrentLocation.class);
        startActivity(mapActivityIntent);
        finish();
    }
}
