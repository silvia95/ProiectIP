package com.ip.mvc.controllers;

import com.ip.mvc.entities.model.contents.Article;
import com.ip.mvc.entities.model.contents.Journal;
import com.ip.mvc.entities.services.MyActivityService;
import com.ip.mvc.entities.services.ProfileService;
import com.ip.mvc.entities.services.ScientificActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/scientific")
public class ScientificController {

    @Autowired
    private MyActivityService myActivityService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ScientificActivityService scientificActivityService;

    @RequestMapping(value = "/myactivity", method = RequestMethod.GET)
    public ModelAndView displayScientificActivity(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("myactivity");

        String userID = profileService.getUserID(request.getUserPrincipal().getName());

        List<Article> articleList = myActivityService.getArticles(userID);
        System.out.println(articleList.size());
        model.addObject("articleList", articleList);

        return model;
    }

    @RequestMapping(value = "journaldetails", method = RequestMethod.GET)
    public ModelAndView displayJournal(@RequestParam(value = "journalISSN") String journalISSN) {
        ModelAndView model = new ModelAndView("journal");

        Journal journal = scientificActivityService.getJournal(journalISSN);

        model.addObject("journal", journal);

        return model;
    }

}
