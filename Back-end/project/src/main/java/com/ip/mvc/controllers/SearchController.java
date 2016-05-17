package com.ip.mvc.controllers;

import com.ip.mvc.entities.model.contents.Article;
import com.ip.mvc.entities.model.users.Teacher;
import com.ip.mvc.entities.model.users.User;
import com.ip.mvc.entities.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView displayForm(@RequestParam(value = "error", required = false) String error) {
        ModelAndView model = new ModelAndView("search/search");


        return model;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView showResults(@RequestParam(value = "s") String s, @RequestParam(value = "searchType") String searchType) {
        ModelAndView model = new ModelAndView("search/results");

        ArrayList<Article> results = searchService.getSearchResults(s, searchType);

        model.addObject("results", results);

        return model;
    }

}
