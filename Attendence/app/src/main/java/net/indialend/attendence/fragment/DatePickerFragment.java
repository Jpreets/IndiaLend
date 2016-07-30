package net.indialend.attendence.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import net.indialend.attendence.activity.LeaveActivity;

import java.util.Calendar;

/**
 * Created by jaspreetsingh on 7/27/16.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public LeaveActivity leaveActivity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
        // arg1 = year
        // arg2 = month
        // arg3 = day
        leaveActivity.showDate(arg1, arg2+1, arg3);
    }

    public static DatePickerFragment getInstance(LeaveActivity leaveActivity) {

        DatePickerFragment datePickerFragment =  new DatePickerFragment();
        datePickerFragment.leaveActivity = leaveActivity;
         return datePickerFragment;

    }
}
