package com.sekar.quizzy.model;
public class Connections {
    String img1;
    String img2;
    String answer;

    public Connections(String img1, String img2,String answer) {
        this.img1 = img1;
        this.img2 = img2;
        this.answer=answer;
    }

    public Connections(){

    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }
}
