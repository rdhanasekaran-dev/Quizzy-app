package com.sekar.quizzy.model;

public class Negatives {
    String img;
    String answer;

    public Negatives(){

    }

    public Negatives(String img,String answer) {
        this.img = img;
        this.answer = answer;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
