package com.ip.mvc.controllers;

import com.ip.mvc.entities.model.users.Teacher;
import com.ip.mvc.entities.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView displayHome(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return new ModelAndView("/admin/home");
        } else {
            ModelAndView model = new ModelAndView("home");
            String userID = profileService.getUserID(request.getUserPrincipal().getName());
            Teacher teacherInfo = profileService.getTeacherInfo(userID);

            request.getSession().setAttribute("userID", userID);

            model.addObject("teacherInfo", teacherInfo);

            return model;
        }
    }
}
