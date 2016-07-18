/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.service;

import java.util.List;
import net.indialend.attendance.bean.Branch;
import net.indialend.attendance.bean.Holiday;

/**
 *
 * @author jaspreetsingh
 */
public interface HolidayService {

    public boolean saveHoliday(Holiday holiday);

    public Holiday getHoliday(long holidayId);

    public List<Holiday> getHoliday(int year);

    public boolean deleteHoliday(long holidayId);

}
