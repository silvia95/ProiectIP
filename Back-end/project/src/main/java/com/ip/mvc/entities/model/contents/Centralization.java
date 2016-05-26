package com.ip.mvc.entities.model.contents;

/**
 * Created by cristian on 26.05.2016.
 */
public class Centralization {

    private int abScore;
    private int totalScore;
    private boolean pass;
    private String actualType;
    private String futureType;

    public void setABScore(int score) {
        this.abScore = score;
    }

    public int getABSCore() {
        return abScore;
    }

    public void setTotalScore(int score) {
        this.totalScore = score;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setPass(boolean pas) {
        this.pass = pas;
    }

    public boolean getPass() {
        return pass;
    }

    public void setActualType(String t) {
        this.actualType = t;
    }

    public String getActualType() {
        return actualType;
    }

    public void setFutureType(String t) {
        this.futureType = t;
    }

    public String getFutureType() {
        return futureType;
    }
}
