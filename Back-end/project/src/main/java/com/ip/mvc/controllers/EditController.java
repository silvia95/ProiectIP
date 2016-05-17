package com.ip.mvc.controllers;

import com.ip.mvc.entities.model.contents.Article;
import com.ip.mvc.entities.model.contents.Project;
import com.ip.mvc.entities.model.contents.Quotation;
import com.ip.mvc.entities.services.EditService;
import com.ip.mvc.entities.services.MyActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value = "/edit")
public class EditController {

    @Autowired
    private MyActivityService myActivityService;
    @Autowired
    private EditService editService;

    @RequestMapping(value = "article", method = RequestMethod.GET)
    public ModelAndView displayArticle(@RequestParam(value = "articleID") String articleID) {
        ModelAndView model = new ModelAndView("edit/article");

        Article article = myActivityService.getArticle(articleID);
        model.addObject("article", article);

        return model;
    }


    @RequestMapping(value = "article", method = RequestMethod.POST)
    public RedirectView editArticle(@ModelAttribute Article article,
                                    @RequestParam(value = "articleID") String articleID) {

        article.setArticleID(articleID);
        editService.editArticle(article);

        return new RedirectView("/scientific/myactivity");
    }

    @RequestMapping(value = "quotation", method = RequestMethod.GET)
    public ModelAndView displayQuotation(@RequestParam(value = "articleID") String articleID) {
        ModelAndView model = new ModelAndView("edit/quotation");

        Quotation quotation = myActivityService.getQuotation(articleID);
        model.addObject("quotation", quotation);
        model.addObject("articleID", articleID);

        return model;
    }

    @RequestMapping(value = "quotation", method = RequestMethod.POST)
    public RedirectView editArticle(@ModelAttribute Quotation quotation) {

        editService.editQuotation(quotation);

        return new RedirectView("/scientific/myactivity");
    }

    @RequestMapping(value = "project", method = RequestMethod.GET)
    public ModelAndView displayProject(@RequestParam(value = "projectID") String projectID) {
        ModelAndView model = new ModelAndView("edit/project");

        Project project = myActivityService.getProject(projectID);

        model.addObject("project", project);
        model.addObject("projectID", projectID);

        return model;
    }

    @RequestMapping(value = "project", method = RequestMethod.POST)
    public RedirectView editArticle(@ModelAttribute Project project) {

        editService.editProject(project);

        return new RedirectView("/scientific/myactivity");
    }


}
