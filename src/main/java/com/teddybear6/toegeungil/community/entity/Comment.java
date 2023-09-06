package com.teddybear6.toegeungil.community.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    /* 댓글 엔티티
     * 댓글 id, o
     * 회원 id, o
     * community 번호 o
     * comment 내용,
     * comment 날짜,
     * comment 수정 날짜
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_num")
    private int commentNum; // 댓글 번호
    @Column(name = "user_num")
    private int userNum; // 회원 번호(작성자)
    @Column(name = "community_num")
    private int communityNum; // community 번호
    @Column(name = "comment_detail", nullable = false)
    private String commentDetail; // comment 내용
    @Column(name = "comment_write_date")
    @Temporal(TemporalType.DATE)
    private Date commentWriteDate; // comment 날짜
    @Column(name = "comment_update_date")
    @Temporal(TemporalType.DATE)
    private Date commentUpdateDate; // comment 수정 날짜





}
