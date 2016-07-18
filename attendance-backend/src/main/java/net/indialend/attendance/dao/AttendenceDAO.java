/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.dao;

import java.util.Date;
import java.util.List;
import net.indialend.attendance.bean.Attendence;

/**
 *
 * @author jaspreetsingh
 */
public interface AttendenceDAO {

    public void persist(Attendence attendence);

    public void update(Attendence attendence);

    public Attendence getByKey(Long attendenceId);

    public List<Attendence> getAttendence(long staffId, Date fromDate, Date toDate);

}
