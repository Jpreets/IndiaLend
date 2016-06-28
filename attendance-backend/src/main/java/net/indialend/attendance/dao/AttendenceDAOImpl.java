/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.indialend.attendance.bean.Attendence;
import net.indialend.attendance.bean.Branch;
import net.indialend.attendance.bean.Staff;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jaspreetsingh
 */
@Repository
public class AttendenceDAOImpl extends AbstractDao<Long, Attendence> implements AttendenceDAO {

    @Override
    public List<Attendence> getAttendence(long staffId, Date fromDate, Date toDate) {
        
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("staff.staffId", staffId));
        crit.add(Restrictions.ge("checkIn", fromDate));
        crit.add(Restrictions.le("checkIn", toDate));

        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        return (List<Attendence>) crit.list();

    }

}
