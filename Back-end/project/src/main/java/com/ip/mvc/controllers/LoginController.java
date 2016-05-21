package com.ip.mvc.controllers;

import com.ip.mvc.entities.model.users.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView displayLogin(@RequestParam(value = "error", required = false) String error) {
        ModelAndView model = new ModelAndView("login");

        if(error!=  null) model.addObject("error", "Invalid username and password!");

        model.addObject("user", new User());
        return model;
    }

    @RequestMapping("favicon.ico")
    public RedirectView favicon() {
        return new RedirectView("/");
    }
}
