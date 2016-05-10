package com.ip.mvc.controllers;

import com.ip.mvc.entities.model.forms.ProfileForm;
import com.ip.mvc.entities.model.users.Teacher;
import com.ip.mvc.entities.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/userdetails", method = RequestMethod.GET)
    public ModelAndView displayUserDetails(@RequestParam(value = "userID") String userID) {
        ModelAndView model = new ModelAndView("profile/userdetails");

        Teacher teacherInfo = profileService.getTeacherInfo(userID);
        teacherInfo.setOtherEmails(profileService.getOtherEmails(teacherInfo.getEmail()));
        teacherInfo.setDepartments(profileService.getDepartments(teacherInfo.getEmail()));

        model.addObject("teacherInfo", teacherInfo);

        return model;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView displayEditProfile(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("profile/edit");
        Teacher teacher = profileService.getTeacherInfo(profileService.getUserID(request.getUserPrincipal().getName()));
        model.addObject("teacher", teacher);
        model.addObject("profileForm", new ProfileForm());
        return model;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editProfile(HttpServletRequest request,
                                    @ModelAttribute ProfileForm profileForm) {
        ModelAndView model = new ModelAndView("profile/edit");

        System.out.println(profileForm);

        Teacher teacher = profileService.getTeacherInfo(profileService.getUserID(request.getUserPrincipal().getName()));

        boolean flag = profileService.editProfile(profileForm, teacher);

        teacher = profileService.getTeacherInfo(profileService.getUserID(request.getUserPrincipal().getName()));

        if(!flag) model.addObject("message", "Wrong password");
        model.addObject("teacher", teacher);
        model.addObject("profileForm", new ProfileForm());

        return model;
    }
}
