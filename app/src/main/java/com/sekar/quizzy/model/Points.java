package com.sekar.quizzy.model;

public class Points {
    String user;
    String points;


    public Points(String user, String points) {
        this.user = user;
        this.points = points;
    }

    public Points(){

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
