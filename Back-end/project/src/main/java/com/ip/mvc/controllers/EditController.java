package com.ip.mvc.controllers;

import com.ip.mvc.entities.model.contents.Article;
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

        System.out.println(article);

        return new RedirectView("/scientific/myactivity");
    }

}
