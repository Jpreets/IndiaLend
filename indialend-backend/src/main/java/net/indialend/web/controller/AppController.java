package net.indialend.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.indialend.web.component.GCMComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.indialend.web.model.User;
import net.indialend.web.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    UserService userService;
    
    @Autowired
    GCMComponent gCMComponent;

    /**
     * This method will list all existing users.
     */
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "userslist";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/user"}, method = RequestMethod.POST)
    @ResponseBody
    public boolean saveUser(User s) {
        Boolean result = false;
        try {
            System.out.println(s);
            userService.saveUser(s);
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@RequestParam String phone, @RequestParam String selectedDate) {
        return userService.findByPhoneAndDate(phone, selectedDate);
    }

    @RequestMapping(value = {"/signIn"}, method = RequestMethod.POST)
    @ResponseBody
    public User signIn(User s) {
        try {
            User u = userService.login(s.getPhone(), s.getPassword());
            if (u != null) {
                u.setActive(true);
                userService.saveUser(u);
                return u;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return s;
    }

    @RequestMapping(value = {"/signUp"}, method = RequestMethod.POST)
    @ResponseBody
    public User signUp(User s) {

        try {
            userService.saveUser(s);
            return s;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = {"/deactivate-{phone}"}, method = RequestMethod.GET)
    public String deactivateUser(@PathVariable String phone) {
        User  user =  userService.findByPhone(phone);
        gCMComponent.setMessage(user,"Deactivate");
        userService.deactivateUser(phone);
        return "redirect:/list";
    }

}
