/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.dao;

import net.indialend.attendance.service.*;
import java.util.List;
import net.indialend.attendance.bean.Branch;

/**
 *
 * @author jaspreetsingh
 */
public interface BranchDAO {

    public void persist(Branch branch);

    public void update(Branch branch);

    public Branch getByKey(Long branchId);

    public List<Branch> getBranch(int offset, int limit);

    public void delete(Branch branch);

    public Object getBranch();

}
