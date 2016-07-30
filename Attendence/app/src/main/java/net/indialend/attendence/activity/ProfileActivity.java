package net.indialend.attendence.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;

import net.indialend.attendence.R;
import net.indialend.attendence.bean.Staff;
import net.indialend.attendence.bean.User;
import net.indialend.attendence.dao.DatabaseHandler;
import net.indialend.attendence.operation.AttendenceStatusOperation;
import net.indialend.attendence.operation.StaffDetailOperation;
import net.indialend.attendence.operation.StaffSaveOperation;

/**
 * Created by jaspreetsingh on 7/6/16.
 */
public class ProfileActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupDrawer();

    }

    public void loadStaff(Staff staff){
        ((EditText) findViewById(R.id.name)).setText(staff.getName());
        ((EditText) findViewById(R.id.email)).setText(staff.getEmail());
        ((EditText) findViewById(R.id.phone)).setText(staff.getPhone());
        ((EditText) findViewById(R.id.currentAddr)).setText(staff.getCurrentAddr());
        ((EditText) findViewById(R.id.permanentAddr)).setText(staff.getPermanentAddr());
        ((EditText) findViewById(R.id.aadhaarNo)).setText(staff.getAadhaarNo());
        ((EditText) findViewById(R.id.panNo)).setText(staff.getPanNo());
        ((EditText) findViewById(R.id.fatherName)).setText(staff.getFatherName());
        ((EditText) findViewById(R.id.motherName)).setText(staff.getMotherName());
        ((EditText) findViewById(R.id.bloodGroup)).setText(staff.getBloodGroup());

        if("FEMALE".equals(staff.getGender()) ){
            ((RadioButton) findViewById(R.id.male)).setChecked(false);
            ((RadioButton) findViewById(R.id.female)).setChecked(true);
        }else{
            ((RadioButton) findViewById(R.id.male)).setChecked(true);
            ((RadioButton) findViewById(R.id.female)).setChecked(false);

        }

    }


    public void updateStaff(View v) {
        Staff  staff = new Staff();

        DatabaseHandler db = new DatabaseHandler(this);
        User user =  db.getUser();
        staff.setStaffId(Long.valueOf(user.getStaffId()));

        staff.setName(((EditText) findViewById(R.id.name)).getText().toString());
        staff.setEmail(((EditText) findViewById(R.id.email)).getText().toString());
        staff.setPhone(((EditText) findViewById(R.id.phone)).getText().toString());
        staff.setCurrentAddr(((EditText) findViewById(R.id.currentAddr)).getText().toString());
        staff.setPermanentAddr(((EditText) findViewById(R.id.permanentAddr)).getText().toString());
        staff.setAadhaarNo(((EditText) findViewById(R.id.aadhaarNo)).getText().toString());
        staff.setPanNo(((EditText) findViewById(R.id.panNo)).getText().toString());
        staff.setFatherName(((EditText) findViewById(R.id.fatherName)).getText().toString());
        staff.setMotherName(((EditText) findViewById(R.id.motherName)).getText().toString());
        staff.setBloodGroup(((EditText) findViewById(R.id.bloodGroup)).getText().toString());
        staff.setGender(((RadioButton)findViewById(R.id.female)).isChecked()?"FEMALE":"MALE");

        new StaffSaveOperation(this, staff).execute();

    }
    public void performReset(View v) {

        new StaffDetailOperation(this,"PROFILE").execute();

    }
}
