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
import net.indialend.attendance.dao.BranchDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jaspreetsingh
 */
@Repository
public class HolidayDAOImpl extends AbstractDao<Long, Holiday> implements HolidayDAO {

    @Override
    public List<Holiday> getHoliday(Date from, Date to) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.ge("holidayDate", from));
        crit.add(Restrictions.le("holidayDate", to));
        return (List<Holiday>) crit.list();
    }
    
     


}
