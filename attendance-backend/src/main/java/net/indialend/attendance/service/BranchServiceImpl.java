/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.service;

import java.util.List;
import net.indialend.attendance.bean.Branch;
import net.indialend.attendance.dao.BranchDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jaspreetsingh
 */
@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchDAO branchDAO;

    public boolean saveBranch(Branch branch) {
        if (branch.getBranchId() != 0) {
            Branch b1 = branchDAO.getByKey(branch.getBranchId());
            b1.setName(branch.getName());
            b1.setManager(branch.getManager());
            b1.setLocation(branch.getLocation());
            b1.setPhone(branch.getPhone());
            branch = b1;
        }

        branchDAO.persist(branch);
        return branch.getBranchId() > 0;
    }

    public Branch getBranch(long branchId) {
        return branchDAO.getByKey(branchId);
    }

    public List<Branch> getBranch(int offset, int limit) {
        return branchDAO.getBranch(offset, limit);
    }

    public boolean deleteBranch(long branchId) {
        Branch b1 = branchDAO.getByKey(branchId);
        branchDAO.delete(b1);
        return true;
    }

}
