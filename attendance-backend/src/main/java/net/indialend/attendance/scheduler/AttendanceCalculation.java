/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.indialend.attendance.bean.Attendence;
import net.indialend.attendance.bean.Holiday;
import net.indialend.attendance.bean.Leaves;
import net.indialend.attendance.bean.Staff;
import net.indialend.attendance.bean.WorkingDays;
import net.indialend.attendance.constant.LeaveType;
import net.indialend.attendance.service.AttendenceService;
import net.indialend.attendance.service.HolidayService;
import net.indialend.attendance.service.StaffService;
import net.indialend.attendance.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author jaspreetsingh
 */
@Component
public class AttendanceCalculation {

    @Autowired
    private StaffService staffService;

    @Autowired
    private AttendenceService attendenceService;

    @Autowired
    private HolidayService holidayService;

    public WorkingDays getWorkingDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        WorkingDays today = null;
        List<WorkingDays> workingDays = holidayService.getWorkingDays();
        for (WorkingDays days : workingDays) {

            if (days.getWorkingDay().getNumVal() == day) {

                today = days;
                break;
            }
        }
        return today;
    }

    public long getTimeSpend(Staff staff, Date date) {
        long timeSpend = 0;
        List<Attendence> todayAttendence = attendenceService
                .getTodayAttendence(staff.getStaffId(), date);
        for (Attendence attendence : todayAttendence) {

            if (attendence.getCheckOut() != null) {

                timeSpend += (attendence.getCheckOut().getTime()
                        - attendence.getCheckIn().getTime());
            }
        }
        return timeSpend;
    }

    // "0 0 * * * *" = the top of every hour of every day. 
    // "*10 * * * * *" = every ten seconds. 
    // "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
    // "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day. 
    // "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays 
    // "0 0 0 25 12 ?" = every Christmas Day at midnight
    //
    //second, minute, hour, day of month, month, day(s) of week
    //(*) means match any
    //*/X means "every X"
    //? ("no specific value") 
    @Scheduled(cron = "1 0 0 * * *")
    public void calculate() {
        System.out.println("----------"+new Date());
        Date date = DateUtil.getDateWithoutTime(new Date());
        date = DateUtil.updateDate(date, -1);

        WorkingDays today = getWorkingDay(date);
        if (today == null || !today.isSelected()) {
            return;
        }

        Holiday holiday = holidayService.getHoliday(date);
        if (holiday != null) {
            return;
        }

        List<Staff> staffs = staffService.getStaff(0, staffService.count().intValue());
        for (Staff staff : staffs) {
            long timeSpend = getTimeSpend(staff, date);
            timeSpend = timeSpend / (60 * 60 * 1000) % 24;

            if (today.getMinWorkingHour() > timeSpend) {
                Leaves leaves = new Leaves();
                leaves.setStaff(staff);
                leaves.setLeaveDate(date);
                leaves.setType(LeaveType.AUTO);

                attendenceService.saveLeave(leaves);
            }
        }
    }

}
