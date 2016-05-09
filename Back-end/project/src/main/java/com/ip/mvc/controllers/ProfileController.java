package com.ip.mvc.controllers;

import com.ip.mvc.entities.model.forms.ProfileForm;
import com.ip.mvc.entities.model.users.Teacher;
import com.ip.mvc.entities.model.users.User;
import com.ip.mvc.entities.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/userdetails", method = RequestMethod.GET)
    public ModelAndView displayUserDetails(@RequestParam(value = "userID") String userID) {
        ModelAndView model = new ModelAndView("profile/userdetails");


        User user = profileService.getUserInfo(userID);

        model.addObject("userInfo", user);
        if(user != null) {
            Teacher teacherInfo = profileService.getTeacherInfo(user);
            model.addObject("teacherInfo", teacherInfo);

            List<String> emails = profileService.getOtherEmails(user);
            model.addObject("emails", emails);
        }

        return model;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView displayEditProfile(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("profile/edit");
        User user = profileService.getUserInfo(profileService.getUserID(request.getUserPrincipal().getName()));
        model.addObject("user", user);
        model.addObject("profileForm", new ProfileForm());
        return model;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editProfile(HttpServletRequest request,
                                    @ModelAttribute ProfileForm profileForm) {
        ModelAndView model = new ModelAndView("profile/edit");

        System.out.println(profileForm);

        User user = profileService.getUserInfo(profileService.getUserID(request.getUserPrincipal().getName()));

        boolean flag = profileService.editProfile(profileForm, user);

        user = profileService.getUserInfo(profileService.getUserID(request.getUserPrincipal().getName()));

        if(!flag) model.addObject("message", "Wrong password");
        model.addObject("user", user);
        model.addObject("profileForm", new ProfileForm());

        return model;
    }
}
