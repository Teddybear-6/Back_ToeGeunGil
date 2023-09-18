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

    private Long imageId; //파일 번호(PK)

    public SocialImageDTO() {
    }

    public SocialImageDTO(SocialImage socialImage) {
        this.id = socialImage.getId();
        this.name = socialImage.getName();
        this.path = socialImage.getPath();
        this.imageId = socialImage.getImageId();
    }

    public SocialImageDTO(int id, String name, String path, Long imageId) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.imageId = imageId;
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

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "SocialImageDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", imageId=" + imageId +
                '}';
    }
}
