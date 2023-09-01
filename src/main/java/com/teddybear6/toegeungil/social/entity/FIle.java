package com.teddybear6.toegeungil.social.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "file")
public class FIle {

    @Id
    @Column(name = "file_num")
    private int fileNum; //파일 번호(PK)

    @Column(name = "file_name")
    private String fileName; //저장될 파일 이름

    @Column(name = "file_ori_name")
    private String fileOriName; //기존 파일 이름

    @Column(name = "file_url")
    private String fileUrl; //저장 경로


}
