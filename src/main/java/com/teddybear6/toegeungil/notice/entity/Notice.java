package com.teddybear6.toegeungil.notice.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Entity(name = "notice")
@Table(name = "notice")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert // insert 시 지정된 default 값을 적용
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_num") // 공지 번호
    private int noticeNum;

    @Column(name = "notice_member") // 회원 번호
    private int noticeMember;
    @Column(name = "notice_title") // 공지 제목
    private String noticeTitle;

    @Column(name = "notice_content") // 공지 내용
    private String noticeContent;

    @Column(name = "notice_date") // 공지 작성일
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date noticeDate;

    @Column(name = "notice_modi_date") // 공지 수정일
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date noticeModiDate;

    @Column(name = "notice_state", columnDefinition = "varchar(1)")  // 공지 상태
    @ColumnDefault("'Y'") /* default 값 설정할 때 사용 */
    private String noticeState;

    public Notice() {
    }

    public Notice(int noticeNum, int noticeMember, String noticeTitle, String noticeContent, Date noticeDate, Date noticeModiDate, String noticeState) {
        this.noticeNum = noticeNum;
        this.noticeMember = noticeMember;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeDate = noticeDate;
        this.noticeModiDate = noticeModiDate;
        this.noticeState = noticeState;
    }

    public int getNoticeNum() {
        return noticeNum;
    }

    public void setNoticeNum(int noticeNum) {
        this.noticeNum = noticeNum;
    }

    public int getNoticeMember() {
        return noticeMember;
    }

    public void setNoticeMember(int noticeMember) {
        this.noticeMember = noticeMember;
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
                "noticeNum=" + noticeNum +
                ", noticeMember=" + noticeMember +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", noticeDate=" + noticeDate +
                ", noticeModiDate=" + noticeModiDate +
                ", noticeState='" + noticeState + '\'' +
                '}';
    }
}
