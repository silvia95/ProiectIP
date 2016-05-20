package com.ip.mvc.entities.model.forms;

/**
 * Created by Andrei on 5/20/2016.
 */
public class ReportForm {

    private ScientificProduction scientificProduction;

    public ScientificProduction getScientificProduction() {
        return scientificProduction;
    }

    public void setScientificProduction(ScientificProduction scientificProduction) {
        this.scientificProduction = scientificProduction;
    }

    @Override
    public String toString() {
        return "ReportForm{" +
                "scientificProduction=" + scientificProduction +
                '}';
    }
}
