/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.indialend.attendance.bean.Branch;
import net.indialend.attendance.bean.Holiday;
import net.indialend.attendance.bean.WorkingDays;
import net.indialend.attendance.constant.WorkingDay;
import net.indialend.attendance.service.BranchService;
import net.indialend.attendance.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author jass
 */
@RequestMapping("/holiday")
@Controller
public class HolidayController {
    
    @Autowired
    private HolidayService holidayService;

    @RequestMapping("/list")
    public ModelAndView List(
            @RequestParam(defaultValue = "0", required = false) int year) {
        ModelAndView view = new ModelAndView("holiday/list");
        
        if(year == 0){
         year =Calendar.getInstance().get(Calendar.YEAR);
        }
        System.out.println("====="+holidayService.getHoliday(year).size());
        
        view.addObject("holidayList", holidayService.getHoliday(year));
        
        List<WorkingDays> workingDays = holidayService.getWorkingDays();
        if(workingDays.isEmpty()){
            for(WorkingDay workingDay  : WorkingDay.values()){
                WorkingDays wd = new WorkingDays();
                wd.setWorkingDay(workingDay);
                workingDays.add(wd);
            }
        }
        view.addObject("workingdays", workingDays);

        return view;
    }

    @RequestMapping("/edit")
    public ModelAndView editBranch(@RequestParam(defaultValue = "0", required = false) long holidayId) {
        ModelAndView view = new ModelAndView("holiday/add");
        if (holidayId != 0) {
            view.addObject("holiday", holidayService.getHoliday(holidayId));
        }
        return view;
    }

    @RequestMapping("/save")
    public ModelAndView saveBranch(Holiday holiday) {
        ModelAndView view = new ModelAndView(new RedirectView("list"));

        holidayService.saveHoliday(holiday);

        return view;
    }

    @RequestMapping("/delete")
    public ModelAndView deleteBranch(String holidayId) {
        ModelAndView view = new ModelAndView(new RedirectView("list"));

        if (holidayId != null) {
            String[] bids = holidayId.split(",");
            for (String bid : bids) {
                holidayService.deleteHoliday(Long.valueOf(bid));
            }

        }
        return view;
    }
    
     @RequestMapping("/saveWorkingDay")
     @ResponseBody
    public Map saveWorkingDay(@RequestBody List<WorkingDays> workingday) {
        System.out.println("----------"+workingday);
        Map map=  new HashMap();
        map.put("success", holidayService.saveWorkingDays(workingday));
        return map;
    }
    
}
