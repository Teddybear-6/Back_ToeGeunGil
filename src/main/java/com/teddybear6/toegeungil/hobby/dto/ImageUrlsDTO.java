package com.teddybear6.toegeungil.hobby.dto;

public class ImageUrlsDTO {

    private int hobbyCode;

    private int id;

    private String name;

    private  String path;


    public ImageUrlsDTO() {
    }

    public ImageUrlsDTO(int hobbyCode, int id, String name, String path) {
        this.hobbyCode = hobbyCode;
        this.id = id;
        this.name = name;
        this.path = path;
    }


    public int getHobbyCode() {
        return hobbyCode;
    }

    public void setHobbyCode(int hobbyCode) {
        this.hobbyCode = hobbyCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ImageUrlsDTO{" +
                "hobbyCode=" + hobbyCode +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}