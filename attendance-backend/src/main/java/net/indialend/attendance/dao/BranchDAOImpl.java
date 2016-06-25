/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.dao;

import net.indialend.attendance.service.*;
import java.util.List;
import net.indialend.attendance.bean.Branch;
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
public class BranchDAOImpl extends AbstractDao<Long, Branch> implements BranchDAO {

    @Override
    public List<Branch> getBranch(int offset, int limit) {
        Criteria crit = createEntityCriteria();
        crit.setFirstResult(offset);
        crit.setMaxResults(limit);
        return (List<Branch>) crit.list();
    }


}
