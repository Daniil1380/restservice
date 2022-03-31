package com.daniil1380.restservice.dto;

import java.util.List;

public class TestWithQuestions extends Test {

    private List<Question> questions;

    private List<Long> idsOfCorrectAnswers;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public TestWithQuestions() {
    }

    public TestWithQuestions(Long id, String testName, List<Question> questions, List<Long> idsOfCorrectAnswers) {
        super(id, testName);
        this.questions = questions;
        this.idsOfCorrectAnswers = idsOfCorrectAnswers;
    }

    public List<Long> getIdsOfCorrectAnswers() {
        return idsOfCorrectAnswers;
    }

    public void setIdsOfCorrectAnswers(List<Long> idsOfCorrectAnswers) {
        this.idsOfCorrectAnswers = idsOfCorrectAnswers;
    }
}
