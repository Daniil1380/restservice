package com.daniil1380.restservice.dto;

public class LoggedUser extends User {

    private Long lastResult;

    private Long startedTest;

    private Long[] pickedAnswerIds;

    public Long getLastResult() {
        return lastResult;
    }

    public void setLastResult(Long lastResult) {
        this.lastResult = lastResult;
    }

    public LoggedUser(Long uuid, String login, String password, Long lastResult, Long startedTest) {
        super(uuid, login, password);
        this.lastResult = lastResult;
        this.startedTest = startedTest;
    }

    public LoggedUser() {
    }

    public Long getStartedTest() {
        return startedTest;
    }

    public void setStartedTest(Long startedTest) {
        this.startedTest = startedTest;
    }


    public Long[] getPickedAnswerIds() {
        return pickedAnswerIds;
    }


    public void setPickedAnswerIds(Long[] pickedAnswerIds) {
        this.pickedAnswerIds = pickedAnswerIds;
    }
}
