package com.teddybear6.toegeungil.notice.dto;

import java.sql.Date;

public class NoticeDetailDTO {
    private int noticeMember; // 관리자 회원 번호
    private int noticeNum; // 공지 번호
    private String noticeTitle; // 공지 제목
    private String noticeContent; // 공지 내용
    private Date noticeModiDate; // 공지 수정일

    public NoticeDetailDTO() {
    }

    public NoticeDetailDTO(int noticeMember, int noticeNum, String noticeTitle, String noticeContent, Date noticeModiDate) {
        this.noticeMember = noticeMember;
        this.noticeNum = noticeNum;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeModiDate = noticeModiDate;
    }

    public int getNoticeMember() {
        return noticeMember;
    }

    public void setNoticeMember(int noticeMember) {
        this.noticeMember = noticeMember;
    }

    public int getNoticeNum() {
        return noticeNum;
    }

    public void setNoticeNum(int noticeNum) {
        this.noticeNum = noticeNum;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Date getNoticeModiDate() {
        return noticeModiDate;
    }

    public void setNoticeModiDate(Date noticeModiDate) {
        this.noticeModiDate = noticeModiDate;
    }

    @Override
    public String toString() {
        return "NoticeDetailDTO{" +
                "noticeMember=" + noticeMember +
                ", noticeNum=" + noticeNum +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeModiDate=" + noticeModiDate +
                '}';
    }
}
