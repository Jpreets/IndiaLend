/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.service;

import java.util.List;
import net.indialend.attendance.bean.Branch;
import net.indialend.attendance.bean.Staff;
import net.indialend.attendance.dao.StaffDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jaspreetsingh
 */
@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDAO staffDAO;

    @Override
    public boolean saveStaff(Staff staff) {

        if (staff.getStaffId() != 0) {
            Staff s1 = staffDAO.getByKey(staff.getStaffId());
            s1.setName(staff.getName());
            s1.setGender(staff.getGender());
            s1.setAadhaarNo(staff.getAadhaarNo());
            s1.setPanNo(staff.getPanNo());
            s1.setEmail(staff.getEmail());
            s1.setPhone(staff.getPhone());
            s1.setCurrentAddr(staff.getCurrentAddr());
            s1.setPermanentAddr(staff.getPermanentAddr());
            s1.setFatherName(staff.getFatherName());
            s1.setMotherName(staff.getMotherName());
            s1.setBloodGroup(staff.getBloodGroup());
            staff = s1;
        }

        staffDAO.persist(staff);
        return staff.getStaffId() > 0;
    }

    @Override
    public Staff getStaff(long staffId) {
        return staffDAO.getByKey(staffId);
    }

    @Override
    public List<Staff> getStaff(int offset, int limit) {
        return staffDAO.getStaff(offset, limit);

    }

    @Override
    public boolean deleteStaff(long staffId) {
        Staff s1 = staffDAO.getByKey(staffId);
        staffDAO.delete(s1);
        return true;
    }

}
