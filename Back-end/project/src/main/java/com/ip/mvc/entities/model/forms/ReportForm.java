package com.ip.mvc.entities.model.forms;

/**
 * Created by Andrei on 5/20/2016.
 */
public class ReportForm {

    private ScientificProduction scientificProduction;
    private ScientificProduction scientificImpact;

    public ScientificProduction getScientificImpact() {
        return scientificImpact;
    }

    public void setScientificImpact(ScientificProduction scientificImpact) {
        this.scientificImpact = scientificImpact;
    }

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
