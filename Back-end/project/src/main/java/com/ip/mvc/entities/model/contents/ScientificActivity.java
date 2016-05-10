package com.ip.mvc.entities.model.contents;

public abstract class ScientificActivity {

    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ScientificActivity{" +
                "score=" + score +
                '}';
    }
}
