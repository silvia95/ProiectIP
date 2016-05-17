package com.ip.mvc.controllers;

import com.ip.mvc.entities.model.contents.Article;
import com.ip.mvc.entities.model.contents.Journal;
import com.ip.mvc.entities.model.contents.Quotation;
import com.ip.mvc.entities.services.MyActivityService;
import com.ip.mvc.entities.services.ProfileService;
import com.ip.mvc.entities.services.ScientificActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
        ModelAndView model = new ModelAndView("scientific/myactivity");

        String userID = profileService.getUserID(request.getUserPrincipal().getName());

        List<Article> articleList = myActivityService.getArticles(userID);
        model.addObject("articleList", articleList);

        return model;
    }

    @RequestMapping(value = "journaldetails", method = RequestMethod.GET)
    public ModelAndView displayJournal(@RequestParam(value = "journalISSN") String journalISSN) {
        ModelAndView model = new ModelAndView("scientific/journal");

        Journal journal = scientificActivityService.getJournal(journalISSN);
        model.addObject("journal", journal);

        return model;
    }

    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public ModelAndView displayArticles() {
        ModelAndView model = new ModelAndView("scientific/articles");

        List<Article> articleList = scientificActivityService.getAllArticles();
        model.addObject("articleList", articleList);

        return model;
    }

    @RequestMapping(value = "/addQuotation", method = RequestMethod.GET)
    public ModelAndView displayAddQuotation(HttpServletRequest request,
                                            @RequestParam(value = "articleID") String articleID) {
        ModelAndView model = new ModelAndView("scientific/addquotation");
        String userID = profileService.getUserID(request.getUserPrincipal().getName());

        model.addObject("userID", userID);
        model.addObject("articleID", articleID);
        model.addObject("quotation", new Quotation());

        return model;
    }

    @RequestMapping(value = "/addQuotation", method = RequestMethod.POST)
    public RedirectView addQuotation(@ModelAttribute Quotation quotation) {

        myActivityService.addQuotation(quotation);
        return new RedirectView("articles");
    }

    @RequestMapping(value = "/quotations", method = RequestMethod.GET)
    public ModelAndView displayQuotations() {
        ModelAndView model = new ModelAndView("scientific/quotations");

        List<Quotation> quotationList = scientificActivityService.getAllQuotations();
        model.addObject("quotationList", quotationList);

        return model;
    }



}
