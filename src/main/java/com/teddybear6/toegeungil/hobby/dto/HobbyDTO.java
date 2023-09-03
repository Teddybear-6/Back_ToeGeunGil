package com.teddybear6.toegeungil.hobby.dto;

public class HobbyDTO {
    private String hobbyName;

    public HobbyDTO(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    public HobbyDTO() {
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    @Override
    public String toString() {
        return "HobbyDTO{" +
                "hobbyName='" + hobbyName + '\'' +
                '}';
    }
}
