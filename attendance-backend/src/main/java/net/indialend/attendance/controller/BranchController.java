/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.controller;

import net.indialend.attendance.bean.Branch;
import net.indialend.attendance.service.BranchService;
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
@RequestMapping("/branch")
@Controller
public class BranchController {

    @Autowired
    private BranchService branchService;

    @RequestMapping("/list")
    public ModelAndView branchList(@RequestParam(defaultValue = "0", required = false) int offset) {
        ModelAndView view = new ModelAndView("branch/branchList");
        view.addObject("branchList", branchService.getBranch(offset, 10));
        return view;
    }

    @RequestMapping("/edit")
    public ModelAndView editBranch(@RequestParam(defaultValue = "0", required = false) long branchId) {
        ModelAndView view = new ModelAndView("branch/addBranch");
        if (branchId != 0) {
            view.addObject("branch", branchService.getBranch(branchId));
        }
        return view;
    }

    @RequestMapping("/save")
    public ModelAndView saveBranch(Branch branch) {
        ModelAndView view = new ModelAndView(new RedirectView("list"));

        branchService.saveBranch(branch);

        return view;
    }

    @RequestMapping("/delete")
    public ModelAndView deleteBranch(String branchId) {
        ModelAndView view = new ModelAndView(new RedirectView("list"));

        if (branchId != null) {
            String[] bids = branchId.split(",");
            for (String bid : bids) {
                branchService.deleteBranch(Long.valueOf(bid));
            }

        }
        return view;
    }

}
