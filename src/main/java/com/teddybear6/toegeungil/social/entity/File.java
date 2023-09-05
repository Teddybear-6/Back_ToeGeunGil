package com.teddybear6.toegeungil.social.entity;

import javax.persistence.*;

//@Entity
//@Table(name = "file")
public class File {

    @Id
    @Column(name = "file_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 관리하는 전략 사용
    private int fileNum; //파일 번호(PK)

    @Column(name = "file_name")
    private String fileName; //저장될 파일 이름 (파일명이 겹치는 것을 방지하기 위해)

    @Column(name = "file_ori_name")
    private String fileOriName; //기존 파일 이름

    @Column(name = "file_url")
    private String fileUrl; //저장 경로

    public File() {
    }

    public File(int fileNum, String fileName, String fileOriName, String fileUrl) {
        this.fileNum = fileNum;
        this.fileName = fileName;
        this.fileOriName = fileOriName;
        this.fileUrl = fileUrl;
    }

    /*Builder*/
    public File fileNum(int fileNum) {
        this.fileNum = fileNum;
        return this;
    }

    public File fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public File fileOriName(String fileOriName) {
        this.fileOriName = fileOriName;
        return this;
    }

    public File fileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
        return this;
    }

    public File builder() {
        return new File(fileNum, fileName, fileOriName, fileUrl);
    }

    /*Getter*/
    public int getFileNum() {
        return fileNum;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileOriName() {
        return fileOriName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileNum=" + fileNum +
                ", fileName='" + fileName + '\'' +
                ", fileOriName='" + fileOriName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}
