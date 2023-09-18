package com.teddybear6.toegeungil.social.dto;

import com.teddybear6.toegeungil.social.entity.SocialImage;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SocialImageDTO {

    private int id;

    private String name;

    private String path;

    private int socialNum;

    public SocialImageDTO() {
    }

    public SocialImageDTO(SocialImage socialImage) {
        this.id = socialImage.getId();
        this.name = socialImage.getName();
        this.path = socialImage.getPath();
        this.socialNum = socialImage.getSocialNum();
    }

    public SocialImageDTO(int id, String name, String path, int socialNum) {
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
        return "SocialImageDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", socialNum=" + socialNum +
                '}';
    }
}
