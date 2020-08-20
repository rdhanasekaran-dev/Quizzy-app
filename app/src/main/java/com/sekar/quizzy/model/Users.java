package com.sekar.quizzy.model;

public class Users {

    String country;
    String deviceid;
    String imageuri;
    String points;
    String username;

    public Users(){

    }

    public Users(String country, String deviceid, String imageuri, String points, String username) {
        this.country = country;
        this.deviceid = deviceid;
        this.imageuri = imageuri;
        this.points = points;
        this.username = username;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
