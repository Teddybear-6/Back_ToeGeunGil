package com.teddybear6.toegeungil.notice.dto;

import com.teddybear6.toegeungil.notice.entity.NoticeImage;

public class NoticeImageDTO {

    private int id;
    private String name;
    private String path;
    private int noticeNum;

    public NoticeImageDTO() {
    }

    public NoticeImageDTO(NoticeImage noticeImage) {
        this.id = noticeImage.getId();
        this.name = noticeImage.getName();
        this.path = noticeImage.getPath();
        this.noticeNum = noticeImage.getNoticeNum();
    }

    public NoticeImageDTO(int id, String name, String path, int noticeNum) {
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
        return "NoticeImageDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", noticeNum=" + noticeNum +
                '}';
    }
}
