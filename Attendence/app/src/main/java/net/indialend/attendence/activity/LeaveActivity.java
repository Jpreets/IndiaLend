package net.indialend.attendence.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import net.indialend.attendence.R;
import net.indialend.attendence.bean.Leave;
import net.indialend.attendence.bean.Staff;
import net.indialend.attendence.bean.User;
import net.indialend.attendence.dao.DatabaseHandler;
import net.indialend.attendence.fragment.DatePickerFragment;
import net.indialend.attendence.operation.AddLeaveOperation;
import net.indialend.attendence.operation.LeaveDetailOperation;
import net.indialend.attendence.operation.StaffDetailOperation;
import net.indialend.attendence.operation.StaffSaveOperation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jaspreetsingh on 7/6/16.
 */
public class LeaveActivity extends CommonActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        setupDrawer();

        new LeaveDetailOperation(this).execute();

    }

    public TextView getTextView(){

        TextView tv = new TextView(this);
        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        tv.setBackgroundResource(R.drawable.cell_shape);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(18);
        tv.setPadding(0, 5, 0, 5);

        return  tv;
    }

    public void openDatePicker(View view) {
        DatePickerFragment newFragment = DatePickerFragment.getInstance(this);
        newFragment.show(getFragmentManager(),"datePicker");
    }



    public void showDate(int year, int month, int day) {
        ((EditText)findViewById(R.id.leaveDate)).setText(new StringBuilder().append(year).append("/")
                .append(month).append("/").append(day));
    }


    public void buildTable(List<Leave> leaves) {

        TableLayout table_layout = (TableLayout) findViewById(R.id.tableLayout1);
        table_layout.removeAllViews();

        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        TextView textView = getTextView();
        textView.setText("Date");
        row.addView(textView);

        TextView textView1 = getTextView();
        textView1.setText("Type");
        row.addView(textView1);

        TextView textView2 = getTextView();
        textView2.setText("Description");
        row.addView(textView2);

        table_layout.addView(row);
        // outer for loop
        for (Leave leave : leaves) {

             row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            // inner for loop
             textView = getTextView();
            textView.setText(new SimpleDateFormat("yyyy/MM/dd").format(new Date(Long.parseLong(leave.getLeaveDate()))));
            row.addView(textView);

             textView1 = getTextView();
            textView1.setText(leave.getType());
            row.addView(textView1);

            textView2 = getTextView();
            textView2.setText(leave.getDetail());
            row.addView(textView2);

            table_layout.addView(row);

        }
    }


    public void clearFields(){
        ((EditText) findViewById(R.id.leaveDate)).setText("");
        ((EditText) findViewById(R.id.leaveDescription)).setText("");

    }
    public void saveLeave(View v) {

        Leave leave= new Leave();
        leave.setLeaveDate(((EditText) findViewById(R.id.leaveDate)).getText().toString());
        leave.setDetail(((EditText) findViewById(R.id.leaveDescription)).getText().toString());

        new AddLeaveOperation(this,leave).execute();

    }
}
