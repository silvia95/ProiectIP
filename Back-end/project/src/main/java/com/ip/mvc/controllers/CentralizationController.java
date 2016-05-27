package com.ip.mvc.controllers;

import com.ip.mvc.entities.model.contents.Centralization;
import com.ip.mvc.entities.services.CentralizeService;
import com.ip.mvc.entities.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cristian on 26.05.2016.
 */
@Controller
@RequestMapping(value = "/centralize")
public class CentralizationController {

    @Autowired
    private CentralizeService centralizeService;

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(value = "userID") String userID) {
        // create the centralize object
        Centralization cent;
        cent = centralizeService.compute(userID);
        cent.setTeacher(profileService.getTeacherInfo(userID));

        // send it to the view
        ModelAndView model = new ModelAndView("centralize");
        model.addObject("cent", cent);

        return model;
    }
}
