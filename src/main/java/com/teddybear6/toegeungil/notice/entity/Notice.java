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

    
}
