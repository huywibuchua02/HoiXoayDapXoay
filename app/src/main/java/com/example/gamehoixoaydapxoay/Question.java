package com.example.gamehoixoaydapxoay;

import java.util.List;

public class Question {

    private int number;
    private String content;
    private List<Answer> listAnswer;

    public Question(int number, String content, List<Answer> listAnswer) {
        this.number = number;
        this.content = content;
        this.listAnswer = listAnswer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Answer> getListAnswer() {
        return listAnswer;
    }

    public void setListAnswer(List<Answer> listAnswer) {
        this.listAnswer = listAnswer;
    }
}
