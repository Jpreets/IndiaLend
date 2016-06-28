/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.dao;

import java.util.List;
import net.indialend.attendance.bean.Branch;
import net.indialend.attendance.bean.Staff;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jaspreetsingh
 */
@Repository
public class StaffDAOImpl extends AbstractDao<Long, Staff> implements StaffDAO {

    @Override
    public List<Staff> getStaff(int offset, int limit) {
        Criteria crit = createEntityCriteria();
        crit.setFirstResult(offset);
        crit.setMaxResults(limit);
        return (List<Staff>) crit.list();
    }

}
