/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.service;

import java.util.Date;
import java.util.List;
import net.indialend.attendance.bean.Attendence;
import net.indialend.attendance.bean.Leaves;
import net.indialend.attendance.bean.Staff;

/**
 *
 * @author jaspreetsingh
 */
public interface AttendenceService {

    public boolean saveAttendence(Attendence attendence);

    public List<Attendence> getTodayAttendence(long staffId, Date date);

    public List<Attendence> getWeekAttendence(long staffId, Date date);

    public List<Attendence> getMonthAttendence(long staffId, Date date);

    public List<Attendence> getYearAttendence(long staffId, Date date);

    public boolean saveLeave(Leaves leaves);

    public List<Leaves> getLeaves(long staffId, int year);

}
