package com.projetopublicado;
import java.io.Serializable;

public class Match implements Serializable {
    String home_name;
    String away_name;

    public String getHome_name() {
        return home_name;
    }

    public void setHome_name(String home_name) {
        this.home_name = home_name;
    }

    public String getAway_name() {
        return away_name;
    }

    public void setAway_name(String away_name) {
        this.away_name = away_name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    String score;

    @Override
    public String toString() {
        return home_name + "\n" + away_name + "\n" +  score;
    }}

