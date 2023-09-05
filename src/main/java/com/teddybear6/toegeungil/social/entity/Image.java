package com.teddybear6.toegeungil.social.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "file")
public class Image {

    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 관리하는 전략 사용
    private Long imageId; //파일 번호(PK)

    @Column(name = "image_name")
    private String imageName; //저장될 파일 이름 (파일명이 겹치는 것을 방지하기 위해)

    @Column(name = "image_type")
    private String imageType; //기존 파일 이름

    @Lob
    @Column(name = "image_data")
    private byte[] imageData; //저장 경로

    public Image() {
    }

    public Image(Long imageId, String imageName, String imageType, byte[] imageData) {
        this.imageId = imageId;
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageData = imageData;
    }


    /*Builder*/
    public Image imageId(Long imageId) {
        this.imageId = imageId;
        return this;
    }

    public Image imageName(String imageName) {
        this.imageName = imageName;
        return this;
    }

    public Image imageType(String imageType) {
        this.imageType = imageType;
        return this;
    }

    public Image imageData(byte[] imageData) {
        this.imageData = imageData;
        return this;
    }

    public Image builder() {
        return new Image(imageId, imageName, imageType, imageData);
    }


    /*Getter*/
    public Long getImageId() {
        return imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public byte[] getImageData() {
        return imageData;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                ", imageName='" + imageName + '\'' +
                ", imageType='" + imageType + '\'' +
                ", imageData=" + Arrays.toString(imageData) +
                '}';
    }
}
