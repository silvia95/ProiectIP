package com.ip.mvc.controllers;

import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public RedirectView uploadFileHandler(@RequestParam("multiPartFile") MultipartFile multiPartFile) {

        if (!multiPartFile.isEmpty()) {
            try {
                InputStreamReader input = new InputStreamReader(multiPartFile.getInputStream());


                /*

                PDF pdf = new PDF(input);
                List<Journal> journals = pdf.geJournals();

                 */

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new RedirectView("/admin");
    }
}
