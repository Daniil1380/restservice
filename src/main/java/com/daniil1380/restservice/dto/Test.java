package com.daniil1380.restservice.dto;

import java.util.List;

public class Test {

    private List<Question> questions;

    private Integer id;

    private List<Integer> idsOfCorrectAnswers;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Test() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Test(List<Question> questions, Integer id, List<Integer> idsOfCorrectAnswers) {
        this.questions = questions;
        this.id = id;
        this.idsOfCorrectAnswers = idsOfCorrectAnswers;
    }

    public List<Integer> getIdsOfCorrectAnswers() {
        return idsOfCorrectAnswers;
    }

    public void setIdsOfCorrectAnswers(List<Integer> idsOfCorrectAnswers) {
        this.idsOfCorrectAnswers = idsOfCorrectAnswers;
    }
}
