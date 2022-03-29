package com.daniil1380.restservice.dto;

public class LoggedUser extends User {

    private Integer lastResult;

    private Integer startedTest;

    private int[] pickedAnswerIds;

    public Integer getLastResult() {
        return lastResult;
    }

    public void setLastResult(Integer lastResult) {
        this.lastResult = lastResult;
    }

    public LoggedUser(Integer uuid, String login, String password, Integer lastResult, Integer startedTest) {
        super(uuid, login, password);
        this.lastResult = lastResult;
        this.startedTest = startedTest;
    }

    public LoggedUser() {
    }

    public Integer getStartedTest() {
        return startedTest;
    }

    public void setStartedTest(Integer startedTest) {
        this.startedTest = startedTest;
    }


    public int[] getPickedAnswerIds() {
        return pickedAnswerIds;
    }


    public void setPickedAnswerIds(int[] pickedAnswerIds) {
        this.pickedAnswerIds = pickedAnswerIds;
    }
}
