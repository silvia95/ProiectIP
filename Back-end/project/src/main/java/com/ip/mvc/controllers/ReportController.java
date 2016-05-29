package com.ip.mvc.controllers;

import com.ip.mvc.entities.model.contents.Article;
import com.ip.mvc.entities.model.contents.Centralization;
import com.ip.mvc.entities.model.forms.ReportForm;
import com.ip.mvc.entities.model.forms.ScientificProduction;
import com.ip.mvc.entities.services.CentralizeService;
import com.ip.mvc.entities.services.MyActivityService;
import com.ip.mvc.entities.services.ProfileService;
import com.ip.mvc.entities.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrei on 5/20/2016.
 */
@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private MyActivityService myActivityService;
    @Autowired
    private CentralizeService centralizeService;

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ModelAndView displayReportView() {
        ModelAndView model = new ModelAndView("report");

        model.addObject("reportForm", new ReportForm());

        return model;
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public ModelAndView executeReportView(@ModelAttribute ReportForm reportForm,
                                          @RequestParam(value = "productionCheckBox", required = false) String productionCheckBox,
                                          @RequestParam(value = "impactCheckBox", required = false) String impactCheckBox,
                                          @RequestParam(value = "centralizeCheckBox", required = false) String centralizeCheckBox,
                                          HttpServletRequest request) {

        ModelAndView model = new ModelAndView("report");

        String userID = profileService.getUserID(request.getUserPrincipal().getName());

        List<Article> articlesProduction = new ArrayList<>();
        List<Article> articlesImpact = new ArrayList<>();

        if (productionCheckBox != null) {
            ScientificProduction scientificProduction = reportForm.getScientificProduction();
            List<Article> articleList = reportService.getScientificProduction(userID, scientificProduction);
            for (Article article : articleList) {
                articlesProduction.add(myActivityService.getArticleInfo(article.getArticleID()));
            }
        }
        if (impactCheckBox != null) {
            ScientificProduction scientificImpact = reportForm.getScientificImpact();
            List<Article> articleList = reportService.getScientificImpact(userID, scientificImpact);
            for (Article article : articleList) {
                articlesImpact.add(myActivityService.getArticleDetails(article.getArticleID()));
            }
        }
        if (centralizeCheckBox != null) {
            // create the centralize object
            Centralization cent;
            cent = centralizeService.compute(userID);
            cent.setTeacher(profileService.getTeacherInfo(userID));

            // send it to the view
            model.addObject("cent", cent);
        }


        model.addObject("articlesProduction", articlesProduction);
        model.addObject("articlesImpact", articlesImpact);
        model.addObject("reportForm", new ReportForm());

        return model;
    }

}
