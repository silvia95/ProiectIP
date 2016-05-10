package com.ip.mvc.entities.model.contents;

public class Journal extends ScientificActivity {

    private String ISSN;
    private String name;

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
        return "Journal{" +
                "ISSN='" + ISSN + '\'' +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}
