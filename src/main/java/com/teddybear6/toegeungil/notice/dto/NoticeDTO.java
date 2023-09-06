package com.teddybear6.toegeungil.notice.dto;

import java.sql.Date;

public class NoticeDTO {

    private int num; // 관리자 회원 번호?? (수정하기)
    private int noticeNum; // 공지 번호
    private String noticeTitle; // 공지 제목
    private String noticeContent; // 공지 내용
    private Date noticeDate; // 공지 작성일
    private Date noticeModiDate; // 공지 수정일
    private String noticeState; // 공지 상태

    public NoticeDTO() {
    }

    public NoticeDTO(int num, int noticeNum, String noticeTitle, String noticeContent, Date noticeDate, Date noticeModiDate, String noticeState) {
        this.num = num;
        this.noticeNum = noticeNum;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeDate = noticeDate;
        this.noticeModiDate = noticeModiDate;
        this.noticeState = noticeState;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

    public Date getNoticeModiDate() {
        return noticeModiDate;
    }

    public void setNoticeModiDate(Date noticeModiDate) {
        this.noticeModiDate = noticeModiDate;
    }

    public String getNoticeState() {
        return noticeState;
    }

    public void setNoticeState(String noticeState) {
        this.noticeState = noticeState;
    }

    @Override
    public String toString() {
        return "NoticeDTO{" +
                "num=" + num +
                ", noticeNum=" + noticeNum +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeDate=" + noticeDate +
                ", noticeModiDate=" + noticeModiDate +
                ", noticeState='" + noticeState + '\'' +
                '}';
    }
}
