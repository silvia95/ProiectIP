package com.ip.mvc.entities.model.contents;


import java.sql.ResultSet;
import java.sql.SQLException;

public class Visitation {

    private String visitID;
    private String userID;
    private String universityName;
    private int numberOfMonths;
    private int score;
    private int topPosition;

    public Visitation() {

    }

    public Visitation(ResultSet resultSet) throws SQLException {
        this.visitID = resultSet.getString("VISIT_ID");
        this.userID = resultSet.getString("USER_ID");
        this.universityName = resultSet.getString("UNIVERSITY_NAME");
        this.numberOfMonths = resultSet.getInt("NR_OF_MONTHS");
        this.score = resultSet.getInt("SCORE");
        this.topPosition = resultSet.getInt("RANK");
    }

    public String getVisitID() {
        return visitID;
    }

    public void setVisitID(String visitID) {
        this.visitID = visitID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public int getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTopPosition() {
        return topPosition;
    }

    public void setTopPosition(int topPosition) {
        this.topPosition = topPosition;
        if (topPosition < 100) {
            score = 8 * getNumberOfMonths();
        } else if (topPosition < 200) {
            score = 4 * getNumberOfMonths();
        } else if (topPosition < 500) {
            score = 2 * getNumberOfMonths();
        } else if (topPosition < 1000) {
            score = 1 * getNumberOfMonths();
        }
    }

    @Override
    public String toString() {
        return "Visitation{" +
                "visitID='" + visitID + '\'' +
                ", userID='" + userID + '\'' +
                ", universityName='" + universityName + '\'' +
                ", numberOfMonths=" + numberOfMonths +
                ", score=" + score +
                '}';
    }
}
