package com.teddybear6.toegeungil.hobby.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "hobbyImage")
public class HobbyImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image_name")
    private String name;

    @Column(name = "image_type")
    private String type;


    @Lob
    @Column(name="imagedata",length = 1000)
    private byte[] imageDate;

    public HobbyImage() {
    }


    public HobbyImage(int id, String name, String type, byte[] imageDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.imageDate = imageDate;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImageDate() {
        return imageDate;
    }

    public void setImageDate(byte[] imageDate) {
        this.imageDate = imageDate;
    }

    @Override
    public String toString() {
        return "hobbyImage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", imageDate=" + Arrays.toString(imageDate) +
                '}';
    }
}
