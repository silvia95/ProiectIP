package com.ip.mvc.controllers;

import com.ip.mvc.entities.model.contents.*;
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

        List<Quotation> quotationList = myActivityService.getQuotations(userID);
        model.addObject("quotationList", quotationList);

        List<Project> projectList = myActivityService.getProjects(userID);
        model.addObject("projectList", projectList);

        List<Conference> conferenceList = myActivityService.getConferences(userID);
        model.addObject("conferenceList", conferenceList);

        return model;
    }

    @RequestMapping(value = "/myactivity/addQuotation", method = RequestMethod.GET)
    public ModelAndView displayAddArticleQuotation(@RequestParam(value = "articleID") String articleID,
                                            HttpServletRequest request) {

        ModelAndView model = new ModelAndView("scientific/addquotation");

        String userID = profileService.getUserID(request.getUserPrincipal().getName());

        model.addObject("userID", userID);
        model.addObject("articleID", articleID);
        model.addObject("quotation", new Quotation());


        return model;
    }

    @RequestMapping(value = "/myactivity/addQuotation", method = RequestMethod.POST)
    public RedirectView addArticleQuotation(@ModelAttribute Quotation quotation,
                                            @RequestParam(value = "articleID") String articleID) {
        RedirectView model = new RedirectView("/scientific/myactivity");

        quotation.setArticleID(articleID);

        myActivityService.addQuotation(quotation);

        return model;
    }

    @RequestMapping(value = "/myactivity/addArticle", method = RequestMethod.GET)
    public ModelAndView addArticle(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("scientific/addarticle");

        model.addObject("article", new Article());

        return model;
    }
    @RequestMapping(value = "/myactivity/addArticle", method = RequestMethod.POST)
    public RedirectView executeAddArticle(@ModelAttribute Article article,
                                          HttpServletRequest request) {
        RedirectView model = new RedirectView("/scientific/myactivity");

        System.out.println(article);

        String userID = profileService.getUserID(request.getUserPrincipal().getName());

        article.setUserID(userID);

        myActivityService.addArticle(article);

        return model;
    }

    @RequestMapping(value = "/myactivity/addProject", method = RequestMethod.GET)
    public ModelAndView displayAddProject() {
        ModelAndView model = new ModelAndView("scientific/addproject");

        model.addObject("project", new Project());

        return model;
    }

    @RequestMapping(value = "/myactivity/addProject", method = RequestMethod.POST)
    public RedirectView executeAddProject(@ModelAttribute Project project,
                                          HttpServletRequest request) {
        RedirectView model = new RedirectView("/scientific/myactivity");

        String userID = profileService.getUserID(request.getUserPrincipal().getName());

        project.setUserID(userID);

        myActivityService.addProject(project);

        return model;
    }

    @RequestMapping(value = "/myactivity/addConference", method = RequestMethod.GET)
    public ModelAndView displayAddConference() {
        ModelAndView model = new ModelAndView("scientific/addconference");

        model.addObject("conference", new Conference());

        return model;
    }
    @RequestMapping(value = "/myactivity/addConference", method = RequestMethod.POST)
    public RedirectView executeAddConference(@ModelAttribute Conference conference,
                                          HttpServletRequest request) {
        RedirectView model = new RedirectView("/scientific/myactivity");

        String userID = profileService.getUserID(request.getUserPrincipal().getName());

        myActivityService.addConference(conference, userID);

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

    @RequestMapping(value = "/quotations", method = RequestMethod.GET)
    public ModelAndView displayQuotations() {
        ModelAndView model = new ModelAndView("scientific/quotations");

        List<Quotation> quotationList = scientificActivityService.getAllQuotations();
        model.addObject("quotationList", quotationList);

        return model;
    }

    @RequestMapping(value = "/articleDetails", method = RequestMethod.GET)
    public ModelAndView dispalyArticleDetails(@RequestParam(value = "articleID") String articleID) {
        ModelAndView model = new ModelAndView("scientific/article");

        Article article = myActivityService.getArticleDetails(articleID);

        model.addObject("article", article);

        System.out.println(article);

        return model;
    }


}
