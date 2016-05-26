package com.ip.mvc.controllers;

import com.ip.mvc.entities.services.CentralizeService;
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
public class CentralizeController {

    @Autowired
    private CentralizeService centralizeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("centralize");


        return model;
    }
}
