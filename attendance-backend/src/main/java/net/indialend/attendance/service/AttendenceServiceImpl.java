/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.service;

import java.util.Date;
import java.util.List;
import net.indialend.attendance.bean.Attendence;
import net.indialend.attendance.bean.Branch;
import net.indialend.attendance.bean.Leaves;
import net.indialend.attendance.bean.Staff;
import net.indialend.attendance.constant.DateFilter;
import net.indialend.attendance.dao.AttendenceDAO;
import net.indialend.attendance.dao.LeaveDAO;
import net.indialend.attendance.dao.StaffDAO;
import net.indialend.attendance.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jaspreetsingh
 */
@Service
@Transactional
public class AttendenceServiceImpl implements AttendenceService {

    @Autowired
    private AttendenceDAO attendenceDAO;

    @Autowired
    private LeaveDAO leaveDAO;

    @Override
    public boolean saveAttendence(Attendence attendence) {

        if (attendence.getAttendenceId() != 0) {
            Attendence a1 = attendenceDAO.getByKey(attendence.getAttendenceId());
            a1.setCheckOut(new Date());
            a1.setChkOutLat(attendence.getChkOutLat());
            a1.setChkOutLong(attendence.getChkOutLong());
            attendence = a1;
        } else {
            attendence.setCheckIn(new Date());
            attendence.setChkOutLat(0);
            attendence.setChkOutLong(0);

        }
        attendenceDAO.persist(attendence);
        return attendence.getAttendenceId() > 0;

    }

    @Override
    public List<Attendence> getTodayAttendence(long staffId, Date date) {
        Date dateWithoutTime = DateUtil.getDateWithoutTime(date);
        return attendenceDAO.getAttendence(staffId, dateWithoutTime,
                DateUtil.update(dateWithoutTime, DateFilter.DAY, 1));

    }

    @Override
    public List<Attendence> getWeekAttendence(long staffId, Date date) {
        Date dateWithoutTime = DateUtil.getDateWithoutTime(date);
        return attendenceDAO.getAttendence(staffId,
                DateUtil.update(dateWithoutTime, DateFilter.DAY, -7),
                DateUtil.update(dateWithoutTime, DateFilter.DAY, 1));
    }

    @Override
    public List<Attendence> getMonthAttendence(long staffId, Date date) {
        Date dateWithoutTime = DateUtil.getDateWithoutTime(date);
        return attendenceDAO.getAttendence(staffId,
                DateUtil.update(dateWithoutTime, DateFilter.MONTH, -1),
                DateUtil.update(dateWithoutTime, DateFilter.DAY, 1));
    }

    @Override
    public List<Attendence> getYearAttendence(long staffId, Date date) {
        Date dateWithoutTime = DateUtil.getDateWithoutTime(date);
        return attendenceDAO.getAttendence(staffId,
                DateUtil.update(dateWithoutTime, DateFilter.YEAR, -1),
                DateUtil.update(dateWithoutTime, DateFilter.DAY, 1)
        );
    }

    @Override
    public boolean saveLeave(Leaves leaves) {
        leaveDAO.persist(leaves);
        return leaves.getLeaveId() > 0;
    }

    @Override
    public List<Leaves> getLeaves(long staffId, int year) {
        Date startDate = DateUtil.getYearStartDate(year);
        return leaveDAO.getLeaves(staffId, startDate,
                DateUtil.update(startDate, DateFilter.YEAR, 1));

    }

}
