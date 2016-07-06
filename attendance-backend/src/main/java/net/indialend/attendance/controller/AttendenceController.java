/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.indialend.attendance.bean.Attendence;
import net.indialend.attendance.bean.Branch;
import net.indialend.attendance.bean.Staff;
import net.indialend.attendance.service.AttendenceService;
import net.indialend.attendance.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author jaspreetsingh
 */
@RequestMapping("/attendence")
@Controller
public class AttendenceController {

    @Autowired
    private AttendenceService attendenceService;

    @Autowired
    private StaffService staffService;

    @RequestMapping("/save")
    @ResponseBody
    public long saveAttendence(Attendence attendence, long staffId) {
        System.out.println("saveAttendence");

        if (staffId > 0) {
            attendence.setStaff(staffService.getStaff(staffId));
            attendenceService.saveAttendence(attendence);
            return attendence.getAttendenceId();
        }
        return 0;
    }
    
     @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    @ResponseBody
    public long staffLogin(long staffId, String password) {
        System.out.println("login");
        try {
            Staff s = staffService.getStaff(staffId);
            if (s.getPassword().equals(password)) {
                return s.getStaffId();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @RequestMapping("/list")
    public ModelAndView staffList(@RequestParam(defaultValue = "DAY", required = true) long staffId,
            @RequestParam(defaultValue = "MONTH", required = false) String type,
            @RequestParam(required = false) Date fromDate) {
        ModelAndView view = new ModelAndView("attendence/attendenceList");

        if (fromDate == null) {
            fromDate = new Date();
        }

        List<Attendence> attendence = null;
        switch (type) {
            case "DAY":
                attendence = attendenceService.getTodayAttendence(staffId, fromDate);
                break;
            case "WEEK":
                attendence = attendenceService.getWeekAttendence(staffId, fromDate);
                break;
            case "MONTH":
                attendence = attendenceService.getMonthAttendence(staffId, fromDate);
                break;
            case "YEAR":
                attendence = attendenceService.getYearAttendence(staffId, fromDate);
                break;
            
        }
        view.addObject("attendenceList", attendence);

        if(attendence == null || attendence.size()==0){
             view.addObject("staff", this.staffService.getStaff(staffId));

        }else{
            view.addObject("staff", attendence.get(0).getStaff());
        }
        
        return view;
    }

}
