package com.ip.mvc.entities.model.contents;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScientificEvent {

    private String ID;
    private String name;
    private int year;
    private String link;
    private int score;
    private String attendingType;

    public ScientificEvent() {

    }

    public ScientificEvent(ResultSet resultSet) throws SQLException {
        this.ID = resultSet.getString("EVENT_ID");
        this.name = resultSet.getString("EVENT_NAME");
        this.year = resultSet.getInt("EVENT_YEAR");
        this.link = resultSet.getString("EVENT_LINK");
        this.score = resultSet.getInt("SCORE");
        if (score == 1) {
            this.attendingType = "Member";
        } else this.attendingType = "Director";
    }

    public String getAttendingType() {
        return attendingType;
    }

    public void setAttendingType(String attendingType) {
        this.attendingType = attendingType;
        if(attendingType.equals("Member")) score = 1;
        else if(attendingType.equals("Director")) score = 2;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ScientificEvent{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", link='" + link + '\'' +
                ", score=" + score +
                '}';
    }
}
