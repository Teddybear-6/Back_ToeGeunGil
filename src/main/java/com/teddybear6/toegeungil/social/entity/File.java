package com.teddybear6.toegeungil.social.entity;

import javax.persistence.*;

@Entity(name= "file")
@Table(name = "file")
public class File {

    @Id
    @Column(name = "file_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 관리하는 전략 사용
    private int fileNum; //파일 번호(PK)

    @Column(name = "file_name")
    private String fileName; //저장될 파일 이름

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

    public int getFileNum() {
        return fileNum;
    }

    public void setFileNum(int fileNum) {
        this.fileNum = fileNum;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileOriName() {
        return fileOriName;
    }

    public void setFileOriName(String fileOriName) {
        this.fileOriName = fileOriName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
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
