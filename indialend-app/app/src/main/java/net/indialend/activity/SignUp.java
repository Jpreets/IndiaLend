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
import net.indialend.operation.RegistrationOperation;
import net.indialend.service.BootUp;

import org.json.JSONException;

/**
 * Created by jaspreetsingh on 5/10/16.
 */
public class SignUp extends AppCompatActivity {

    String gender= "";
    DatabaseHandler db = new DatabaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activtiy_sign_up);
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
    public void goToSignIn (View v) {
        Intent mapActivityIntent = new Intent(this, SignIn.class);
        startActivity(mapActivityIntent);
        finish();
    }

    public void signUp(View v) throws JSONException {

        EditText name  = (EditText) findViewById(R.id.name);
        String nameStr =name.getText().toString();

        EditText email  = (EditText) findViewById(R.id.email);
        String emailStr =email.getText().toString();

        EditText phone  = (EditText) findViewById(R.id.phone);
        String phoneStr =phone.getText().toString();

        String password =((EditText) findViewById(R.id.password)).getText().toString();

        String cpassword =((EditText) findViewById(R.id.cpassword)).getText().toString();


        if(nameStr == null  ||  nameStr.trim().isEmpty()
                || emailStr == null ||emailStr.trim().isEmpty()
                || phoneStr == null || phoneStr.trim().isEmpty()
                || password == null ||password.trim().isEmpty()
                || cpassword == null ||cpassword.trim().isEmpty()){
            Toast.makeText(this, "Every field is mandatory" , Toast.LENGTH_SHORT );
            return;

        }
        if(!cpassword.equals(password)){
            Toast.makeText(this, "Password does not match" , Toast.LENGTH_SHORT );
            return;
        }


        User user =  new User();
        user.setName(nameStr);
        user.setEmail(emailStr);
        user.setPhone(phoneStr);
        user.setGender(gender);
        user.setPassword(password);
        user.setActive(false);


       new RegistrationOperation(this, user).execute();


    }
}
