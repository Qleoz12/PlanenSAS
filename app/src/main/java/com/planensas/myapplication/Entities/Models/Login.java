package com.planensas.myapplication.Entities.Models;

public class Login {

    private Boolean succes;
    private String resultMessage;
    private String userId;

    public Boolean getSucces() {
        return succes;
    }

    public void setSucces(Boolean succes) {
        this.succes = succes;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
