package com.teddybear6.toegeungil.social.entity;

import com.teddybear6.toegeungil.social.dto.SocialImageDTO;

import javax.persistence.*;

@Entity
@Table(name = "social_image")
public class SocialImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image_origin_name")
    private String name;

    @Column(name = "image_path")
    private String path;

    @Column(name = "social_num")
    private int socialNum;

    public SocialImage() {
    }

    public SocialImage(SocialImageDTO socialImageDTO) {
        this.id = socialImageDTO.getId();
        this.name = socialImageDTO.getName();
        this.path = socialImageDTO.getPath();
        this.socialNum = socialImageDTO.getSocialNum();
    }

    public SocialImage(int id, String name, String path, int socialNum) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.socialNum = socialNum;
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

    public int getSocialNum() {
        return socialNum;
    }

    public void setSocialNum(int socialNum) {
        this.socialNum = socialNum;
    }

    @Override
    public String toString() {
        return "SocialImage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", socialNum=" + socialNum +
                '}';
    }
}
