package com.teddybear6.toegeungil.auth.dto;

public class LoginDTO {

    private String  userId;
    private String userPass;

    public LoginDTO() {
    }


    public LoginDTO(String userId, String userPass) {
        this.userId = userId;
        this.userPass = userPass;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "userId='" + userId + '\'' +
                ", userPass='" + userPass + '\'' +
                '}';
    }
}