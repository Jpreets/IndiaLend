/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.dao;

import java.util.List;
import net.indialend.attendance.service.*;
import net.indialend.attendance.bean.Attendence;
import net.indialend.attendance.bean.Branch;
import net.indialend.attendance.bean.Staff;

/**
 *
 * @author jaspreetsingh
 */
public interface StaffDAO {

    public void persist(Staff branch);

    public void update(Staff branch);

    public void delete(Staff branch);

    public Staff getByKey(Long staffId);
    
    public Long count();

    public List<Staff> getStaff(int offset, int limit);
}
