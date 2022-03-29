package com.daniil1380.restservice.dto;

public class Answer {

    private String answer;

    private Integer id;


    public Answer(String answer, Integer id) {
        this.answer = answer;
        this.id = id;
    }

    public Answer() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
