package com.ip.mvc.controllers;

import java.io.*;
import java.util.List;

import com.ip.mvc.entities.model.pdfImport.Jurnal;
import com.ip.mvc.entities.model.pdfImport.PDF;
import com.ip.mvc.entities.services.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class FileUploadController {

    @Autowired
    private AdminService adminService;

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public RedirectView uploadFileHandler(@RequestParam("multiPartFile") MultipartFile multiPartFile) {

        if (!multiPartFile.isEmpty()) {
            try {

                String fileName = "lista_jurnale.pdf";

                byte[] bytes = multiPartFile.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                logger.info("Server File Location=" + serverFile.getAbsolutePath());

                String filePath = rootPath + File.separator + "tmpFiles" + File.separator + fileName;
                System.out.println(filePath);
                PDF pdfParser = new PDF(filePath);
                List<Jurnal> jurnalList = pdfParser.getJournals();
                for (Jurnal jurnal : jurnalList) {
                    adminService.addJournal(jurnal);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new RedirectView("/admin");
    }
}
