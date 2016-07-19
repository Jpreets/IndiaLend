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
import net.indialend.attendance.bean.WorkingDays;
import net.indialend.attendance.dao.BranchDAO;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jaspreetsingh
 */
@Repository
public class WorkingDayDAOImpl extends AbstractDao<Long, WorkingDays> implements WorkingDayDAO {
    
      public int truncate() {
        int recordDeleted = 0;
        try {
            Session s = getSession();
            String hql = "delete from WorkingDays";
            Query q = s.createQuery(hql);
            recordDeleted = q.executeUpdate();
        } catch (HibernateException e) {
        }
        return recordDeleted;
    }


}
