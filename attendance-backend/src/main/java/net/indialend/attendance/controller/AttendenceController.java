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
import net.indialend.attendance.service.AttendenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/")
    @ResponseBody
    public boolean saveBranch(Attendence attendence) {
        return attendenceService.saveAttendence(attendence);
    }

    @RequestMapping("/list")
    public ModelAndView staffList(@RequestParam(defaultValue = "DAY", required = true) long staffId,
            @RequestParam(defaultValue = "DAY", required = false) String type,
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
            default:
                break;
        }
        view.addObject("attendenceList", attendence);

        return view;
    }

}
