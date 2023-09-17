package com.teddybear6.toegeungil.hobby.entity;

import com.teddybear6.toegeungil.hobby.dto.ImageIdDTO;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "hobbyImage")
public class HobbyImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id;

    @Column(name = "image_origin_name")
    private String name;

    @Column(name = "image_path")
    private String path;

    @Column(name = "hobby_code")
    private int hobbyCode;


    public HobbyImage() {
    }

    public HobbyImage(int id, String name, String path, int hobbyCode) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.hobbyCode = hobbyCode;
    }

    public HobbyImage(ImageIdDTO imageIdDTO) {
        this.id = imageIdDTO.getId();
        this.name = imageIdDTO.getName();
        this.path = imageIdDTO.getPath();

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

    public int getHobbyCode() {
        return hobbyCode;
    }

    public void setHobbyCode(int hobbyCode) {
        this.hobbyCode = hobbyCode;
    }

    @Override
    public String toString() {
        return "HobbyImage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", hobbyCode=" + hobbyCode +
                '}';
    }
}
