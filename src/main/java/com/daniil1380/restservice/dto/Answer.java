package com.daniil1380.restservice.dto;

public class Answer {

    private String answer;

    private Long id;


    public Answer(String answer, Long id) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
