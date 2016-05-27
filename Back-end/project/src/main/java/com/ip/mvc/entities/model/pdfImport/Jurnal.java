/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ip.mvc.entities.model.pdfImport;

/**
 *
 * @author Alin
 */
public class Jurnal {

    private String ISSN;
    private String name;
    private int Score;

    public String getISSN() {
        return ISSN;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Jurnal{" +
                "ISSN='" + ISSN + '\'' +
                ", name='" + name + '\'' +
                ", Score=" + Score +
                '}';
    }

    /**
     * @return the Score
     */
    public int getScore() {
        return Score;
    }

    /**
     * @param Score the Score to set
     */
    public void setScore(String cat) {
        switch (cat){
            case "A":
                this.Score=8;
                break;
            case "B":
                this.Score=4;
                break;
            case "C":
                this.Score=2;
                break;
            default:
                this.Score=1;
        } 
    }
}
