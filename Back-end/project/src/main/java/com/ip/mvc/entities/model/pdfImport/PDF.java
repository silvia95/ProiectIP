package com.ip.mvc.entities.model.pdfImport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jpedal.PdfDecoderInt;
import org.jpedal.PdfDecoderServer;
import org.jpedal.exception.PdfException;
import org.jpedal.grouping.PdfGroupingAlgorithms;
import org.jpedal.objects.PdfPageData;
import static org.jpedal.utils.StringUtils.isNumber;

public class PDF {
    private List<Jurnal> journals;
    private PdfDecoderInt decodePdf;
    private static int defX1 = -1, defX2, defY1, defY2;
    private boolean isCSV = true;
    
    public PDF(String filename){
        journals=new ArrayList<>();
        decodeFile(filename);
    }

    public List<Jurnal> getJournals() {
        return journals;
    }

    public void setJournals(List<Jurnal> journals) {
        this.journals = journals;
    }

    private void decodeFile(String file_name) {
        try {
            decodePdf = new PdfDecoderServer(false);
            decodePdf.setExtractionMode(PdfDecoderServer.TEXT); //extract just text

            PdfDecoderServer.init(true);
            decodePdf.openPdfFile(file_name);
        } catch (final Exception e) {
            System.err.println("Exception " + e + " in pdf code");
        }
        if(!decodePdf.isExtractionAllowed()) {
            System.out.println("Text extraction not allowed");
        } else if (decodePdf.isEncrypted() && !decodePdf.isPasswordSupplied()) {
            System.out.println("Encrypted settings");
        } else {
            final int start = 1;
            final int end = decodePdf.getPageCount();
            try {
                for (int page = start; page < end + 1; page++) { //read pages
                    //decode the page
                    decodePdf.decodePage(page);
                    final PdfGroupingAlgorithms currentGrouping = decodePdf.getGroupingObject();
                    final PdfPageData currentPageData = decodePdf.getPdfPageData();
                    final int x1;
                    final int y1;
                    final int x2;
                    final int y2;
                    if (defX1 == -1) {
                        x1 = currentPageData.getMediaBoxX(page);
                        x2 = currentPageData.getMediaBoxWidth(page) + x1;
                        y2 = currentPageData.getMediaBoxY(page);
                        y1 = currentPageData.getMediaBoxHeight(page) + y2;
                    } else {
                        x1 = defX1;
                        y1 = defY1;
                        x2 = defX2;
                        y2 = defY2;
                    }
                    final Map tableContent;
                    String tableText = null;
                    try {
                        tableContent = currentGrouping.extractTextAsTable(x1, y1, x2, y2, page, isCSV, false, false, false, 0);
                        //get the text from the Map object
                        tableText = (String) tableContent.get("content");
                    } catch (final PdfException e) {
                        decodePdf.closePdfFile();
                        System.err.println("Exception " + e.getMessage() + " with table extraction");
                    }
                    //System.out.println(tableText);
                    String numeJurnal = null;

                    if (tableText != null) {
                        String[] tokens = tableText.split(",");
                    int index=0;
                    int total=0;
                    for (String cuv : tokens)total++;
                    while(index<total){
                            String t=tokens[index];
                            t = t.replace("\"", "");
                            if (t.contains("\n")) {
                                if (isNumber(t.replace("\n", ""))) {
                                    //System.out.println("\nID: " + t.replace("\n", ""));
                                    if(numeJurnal!=null){
                                        Jurnal jur=new Jurnal();
                                        jur.setISSN(numeJurnal.substring(0, 8));
                                        jur.setName(numeJurnal.substring(8, numeJurnal.length()-1));
                                        jur.setScore(numeJurnal.substring(numeJurnal.length()-1,numeJurnal.length()));
                                        journals.add(jur);
                                        numeJurnal = null;
                                    }
                                }
                            }else{
                                if (numeJurnal == null) {
                                        numeJurnal = t;
                                    } else {
                                        numeJurnal = numeJurnal + t;
                                    }
                            }
                            index++;
                        }
                        Jurnal jur=new Jurnal();
                        jur.setISSN(numeJurnal.substring(0, 8));
                        jur.setName(numeJurnal.substring(8, numeJurnal.length()-1));
                        jur.setScore(numeJurnal.substring(numeJurnal.length()-1,numeJurnal.length()));
                        journals.add(jur);
                        
                    }
                    //remove data once written out
                    decodePdf.flushObjectValues(false);
                }
            } catch (final Exception e) {
                decodePdf.closePdfFile();
                System.err.println("Exception " + e.getMessage());
            }
            decodePdf.flushObjectValues(true); //flush any text data read
        }
        decodePdf.closePdfFile();
    }
}
