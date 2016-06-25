/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.service;

import java.util.List;
import net.indialend.attendance.bean.Branch;

/**
 *
 * @author jaspreetsingh
 */
public interface BranchService {

    public boolean saveBranch(Branch branch);

    public Branch getBranch(long branchId);

    public List<Branch> getBranch(int offset, int limit);

}
