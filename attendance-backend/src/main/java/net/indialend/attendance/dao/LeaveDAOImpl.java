/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.dao;

import java.util.Date;
import java.util.List;
import net.indialend.attendance.bean.Holiday;
import net.indialend.attendance.bean.Leaves;
import net.indialend.attendance.bean.Staff;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jaspreetsingh
 */
@Repository
public class LeaveDAOImpl extends AbstractDao<Long, Leaves> implements LeaveDAO {

    @Override
    public List<Leaves> getLeaves(long staffId, Date from, Date to) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("staff.staffId", staffId));
        crit.add(Restrictions.ge("leaveDate", from));
        crit.add(Restrictions.le("leaveDate", to));
        System.out.println(from + "-----" + to);
        return (List<Leaves>) crit.list();
    }

}
