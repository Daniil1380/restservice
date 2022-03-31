package com.daniil1380.restservice.dto;

public class Test {

    private Long id;

    private String testName;

    public Test(Long id, String testName) {
        this.id = id;
        this.testName = testName;
    }

    public Test() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Test(TestWithQuestions testWithQuestions){
        this.id = testWithQuestions.getId();
        this.testName = testWithQuestions.getTestName();
    }
}
