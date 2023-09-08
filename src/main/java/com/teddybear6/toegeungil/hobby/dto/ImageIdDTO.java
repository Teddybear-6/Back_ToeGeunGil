package com.teddybear6.toegeungil.hobby.dto;

public class ImageIdDTO {

    private int id;


    public ImageIdDTO(int id) {
        this.id = id;
    }

    public ImageIdDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ImageIdDTO{" +
                "id=" + id +
                '}';
    }
}
