package com.ip.mvc.controllers;

import com.ip.mvc.entities.model.users.Teacher;
import com.ip.mvc.entities.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView dispalyAdminPanel() {
        ModelAndView model = new ModelAndView("admin/home");

        return model;
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public ModelAndView displayAccounts() {
        ModelAndView model = new ModelAndView("/admin/accounts");

        List<Teacher> accounts = adminService.getAccounts();

        model.addObject("accounts", accounts);

        return model;
    }
}
