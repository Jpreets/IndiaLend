/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.service;

import java.util.List;
import net.indialend.attendance.bean.Attendence;
import net.indialend.attendance.bean.Staff;

/**
 *
 * @author jaspreetsingh
 */
public interface StaffService {

    public boolean saveStaff(Staff staff);

    public Staff getStaff(long staffId);

    public List<Staff> getStaff(int offset, int limit);

    public boolean deleteStaff(long staffId);
    
    public Long count();

}
