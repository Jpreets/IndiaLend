/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.service;

import java.util.Date;
import java.util.List;
import net.indialend.attendance.bean.Branch;
import net.indialend.attendance.bean.Holiday;
import net.indialend.attendance.constant.DateFilter;
import net.indialend.attendance.dao.BranchDAO;
import net.indialend.attendance.dao.HolidayDAO;
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
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayDAO holidayDAO;

    public boolean saveHoliday(Holiday holiday) {
        if (holiday.getHolidayId() != 0) {
            Holiday h1 = holidayDAO.getByKey(holiday.getHolidayId());
            h1.setDetail(holiday.getDetail());
            h1.setHolidayDate(holiday.getHolidayDate());
            holiday = h1;
        }

        holidayDAO.persist(holiday);
        return holiday.getHolidayId() > 0;
    }

    public Holiday getHoliday(long holidayId) {
        return holidayDAO.getByKey(holidayId);
    }

    public List<Holiday> getHoliday(int year) {
        Date startDate = DateUtil.getYearStartDate(year);
        return holidayDAO.getHoliday(startDate,
                DateUtil.update(startDate, DateFilter.YEAR, 1));
    }

    public boolean deleteHoliday(long holidayId) {
        Holiday h1 = holidayDAO.getByKey(holidayId);
        holidayDAO.delete(h1);
        return true;
    }

}
