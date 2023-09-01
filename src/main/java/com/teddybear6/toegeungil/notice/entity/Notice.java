package com.teddybear6.toegeungil.notice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "notice")
public class Notice {

    @Id
    @Column(name = "num") // 회원 번호(pk)
    private int num;

    @Column(name = "notice_num") // 공지 번호
    private int noticeNum;

    @Column(name = "notice_title") // 공지 제목
    private String noticeTitle;

    @Column(name = "notice_content") // 공지 내용
    private String noticeContent;

    @Column(name = "notice_date") // 공지 작성일
    private Date noticeDate;

    @Column(name = "notice_modi_date") // 공지 수정일
    private Date noticeModiDate;

    @Column(name = "notice_state")  // 공지 상태
    private String noticeState;

    public Notice() {
    }

    public Notice(int num, int noticeNum, String noticeTitle, String noticeContent, Date noticeDate, Date noticeModiDate, String noticeState) {
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
        return "Notice{" +
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
