/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.indialend.attendance.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author jaspreetsingh
 */
@Controller
public class IndexController {
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Principal principal) {
        
        ModelAndView model = new ModelAndView("index");
        
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }
        
        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        
        
        if (principal != null) {
            model.setView(new RedirectView("branch/list"));
        }
        return model;
        
    }
}
