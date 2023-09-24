package com.teddybear6.toegeungil.user.dto;

public class FindPassDTO {
    private String name;

    private String nickName;

    private String email;


    public FindPassDTO() {
    }

    public FindPassDTO(String name, String nickName, String email) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "FindPassDTO{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
