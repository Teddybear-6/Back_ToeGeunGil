package com.teddybear6.toegeungil.notice.entity;

import com.teddybear6.toegeungil.notice.dto.NoticeImageDTO;

import javax.persistence.*;

@Entity
@Table(name = "notice_image")
public class NoticeImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image_origin_name")
    private String name;

    @Column(name = "image_path")
    private String path;

    @Column(name = "notice_num")
    private int noticeNum;

    public NoticeImage() {
    }

    public NoticeImage(NoticeImageDTO noticeImageDTO) {
        this.id = noticeImageDTO.getId();
        this.name = noticeImageDTO.getName();
        this.path = noticeImageDTO.getPath();
        this.noticeNum = noticeImageDTO.getNoticeNum();
    }

    public NoticeImage(int id, String name, String path, int noticeNum) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.noticeNum = noticeNum;
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

    public int getNoticeNum() {
        return noticeNum;
    }

    public void setNoticeNum(int noticeNum) {
        this.noticeNum = noticeNum;
    }

    @Override
    public String toString() {
        return "NoticeImage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", noticeNum=" + noticeNum +
                '}';
    }
}
