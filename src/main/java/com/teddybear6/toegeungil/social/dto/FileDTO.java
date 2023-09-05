package com.teddybear6.toegeungil.social.dto;

import com.teddybear6.toegeungil.social.entity.File;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class FileDTO {

    private int fileNum; //파일 번호(PK)
    private String fileName; //저장될 파일 이름
    private String fileOriName; //기존 파일 이름
    private String fileUrl; //저장 경로

    public FileDTO() {
    }

    public File toEntity() { //DTO 객체를 Entity로 변환하는 메서드
        File build = new File()
                .fileNum(fileNum)
                .fileName(fileName)
                .fileOriName(fileOriName)
                .fileUrl(fileUrl)
                .builder();
        return build;
    }

    public FileDTO(int fileNum, String fileName, String fileOriName, String fileUrl) {
        this.fileNum = fileNum;
        this.fileName = fileName;
        this.fileOriName = fileOriName;
        this.fileUrl = fileUrl;
    }

    /*Builder*/
    public FileDTO fileNum(int fileNum) {
        this.fileNum = fileNum;
        return this;
    }

    public FileDTO fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public FileDTO fileOriName(String fileOriName) {
        this.fileOriName = fileOriName;
        return this;
    }

    public FileDTO fileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
        return this;
    }

    public FileDTO builder() {
        return new FileDTO(fileNum, fileName, fileOriName, fileUrl);
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
        return "FileDTO{" +
                "fileNum=" + fileNum +
                ", fileName='" + fileName + '\'' +
                ", fileOriName='" + fileOriName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}
