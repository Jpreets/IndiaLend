/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.dao;

import java.util.Date;
import net.indialend.attendance.service.*;
import java.util.List;
import net.indialend.attendance.bean.Branch;
import net.indialend.attendance.bean.Holiday;

/**
 *
 * @author jaspreetsingh
 */
public interface HolidayDAO {

    public void persist(Holiday holiday);

    public void update(Holiday holiday);

    public Holiday getByKey(Long holidayId);

     public List<Holiday> getHoliday(Date from, Date to);

    public void delete(Holiday holiday);

}
