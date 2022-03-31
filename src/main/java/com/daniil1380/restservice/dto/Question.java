package com.daniil1380.restservice.dto;

import java.util.List;

public class Question {

    private Long id;

    private String question;

    private List<Answer> answers;

    public Question() {
    }

    public Question(Long id, String question, List<Answer> answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
