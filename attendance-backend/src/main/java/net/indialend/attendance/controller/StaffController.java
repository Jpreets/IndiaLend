/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.indialend.attendance.bean.Branch;
import net.indialend.attendance.bean.Staff;
import net.indialend.attendance.compnent.Email;
import net.indialend.attendance.service.BranchService;
import net.indialend.attendance.service.StaffService;
import net.indialend.attendance.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author jaspreetsingh
 */
@RequestMapping("/staff")
@Controller
public class StaffController {
    
    @Autowired
    private StaffService staffService;
    
    @Autowired
    private BranchService branchService;
    
    @Autowired
    private Email email;
    
    @RequestMapping("/list")
    public ModelAndView staffList(@RequestParam(defaultValue = "0", required = false) int offset) {
        ModelAndView view = new ModelAndView("staff/staffList");
        
        view.addObject("staffList", staffService.getStaff(offset, 10));
        
        return view;
    }
    
    @RequestMapping("/edit")
    public ModelAndView editStaff(@RequestParam(defaultValue = "0", required = false) long staffId) {
        ModelAndView view = new ModelAndView("staff/addStaff");
        if (staffId != 0) {
            view.addObject("staff", staffService.getStaff(staffId));
        }
        
        view.addObject("branchList", branchService.getBranch());
        
        return view;
    }
    
    @RequestMapping("/save")
    public ModelAndView saveStaff(Staff staff, @RequestParam(required = true) long branchId) {
        ModelAndView view = new ModelAndView(new RedirectView("list"));
         boolean sendMail= false;
        if (branchId > 0) {
            Branch branch = branchService.getBranch(branchId);
            staff.setBranch(branch);
        }
        
        if (staff.getStaffId() == 0) {
            sendMail = true;
            staff.setPassword(PasswordUtil.randomPassword());   
        }
        
        if (staffService.saveStaff(staff) && sendMail) {
            sendStaffPasswordMail(staff);
           
        }
        
        return view;
    }
    
    @RequestMapping("/delete")
    public ModelAndView deleteStaff(String staffId) {
        ModelAndView view = new ModelAndView(new RedirectView("list"));
        
        if (staffId != null) {
            String[] sids = staffId.split(",");
            for (String sid : sids) {
                staffService.deleteStaff(Long.valueOf(sid));
            }
        }
        
        return view;
    }

    private void sendStaffPasswordMail(Staff staff) {
        try {
            email.sendMail("do-not-reply@gmail.com",staff.getEmail(),
                    "Attendence App Password", "Hi "+staff.getName()+"\n Passwod :"+staff.getPassword());
        } catch (InterruptedException ex) {
            Logger.getLogger(StaffController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
