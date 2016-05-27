package com.ip.mvc.entities.model.pdfImport;


import java.util.List;

public class TestPDF {

    public static void main(String[] args) {

        PDF p = new PDF("D:\\Andrei\\FII\\Anul II\\semestrul II\\IP\\ProiectIP\\Back-end\\project\\src\\main\\java\\com\\ip\\mvc\\entities\\model\\pdfImport\\Lista_jurnale.pdf");
        List<Jurnal> journalList = p.getJournals();

        System.out.println(journalList);

    }

}
