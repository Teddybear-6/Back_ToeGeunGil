package com.teddybear6.toegeungil.hobby.dto;

public class ImageIdDTO {

    private int id;

    private String path;

    private String name;

    private int hobbyCode;

    public ImageIdDTO(int id, String path, String name, int hobbyCode) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.hobbyCode = hobbyCode;
    }



    public ImageIdDTO() {
    }

    public int getId() {
        return id;
    }


    public int getHobbyCode() {
        return hobbyCode;
    }

    public void setHobbyCode(int hobbyCode) {
        this.hobbyCode = hobbyCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ImageIdDTO{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", hobbyCode=" + hobbyCode +
                '}';
    }
}
