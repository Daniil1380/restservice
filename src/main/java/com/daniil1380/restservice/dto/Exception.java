package com.daniil1380.restservice.dto;

public class Exception {

    private String massage;

    private String description;

    public Exception(String massage, String description) {
        this.massage = massage;
        this.description = description;
    }

    public Exception() {
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
