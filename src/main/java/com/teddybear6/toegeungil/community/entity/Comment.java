package com.teddybear6.toegeungil.community.entity;

import com.teddybear6.toegeungil.community.dto.CommentDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentWriteDate; // comment 날짜
    @Column(name = "comment_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentUpdateDate; // comment 수정 날짜

    public Comment() {
    }

    public Comment(int commentNum, int userNum, int communityNum, String commentDetail, Date commentWriteDate, Date commentUpdateDate) {
        this.commentNum = commentNum;
        this.userNum = userNum;
        this.communityNum = communityNum;
        this.commentDetail = commentDetail;
        this.commentWriteDate = commentWriteDate;
        this.commentUpdateDate = commentUpdateDate;
    }

    public Comment(int communityNum, CommentDTO commentDTO) {
        this.commentNum = commentDTO.getCommentNum();
        this.userNum = commentDTO.getUserNum();
        this.communityNum = commentDTO.getCommunityNum();
        this.commentDetail = commentDTO.getCommentDetail();
        this.commentWriteDate = commentDTO.getCommentWriteDate();
        this.commentUpdateDate = commentDTO.getCommentUpdateDate();
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public int getCommunityNum() {
        return communityNum;
    }

    public void setCommunityNum(int communityNum) {
        this.communityNum = communityNum;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

    public Date getCommentWriteDate() {
        return commentWriteDate;
    }

    public void setCommentWriteDate(Date commentWriteDate) {
        this.commentWriteDate = commentWriteDate;
    }

    public Date getCommentUpdateDate() {
        return commentUpdateDate;
    }

    public void setCommentUpdateDate(Date commentUpdateDate) {
        this.commentUpdateDate = commentUpdateDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentNum=" + commentNum +
                ", userNum=" + userNum +
                ", communityNum=" + communityNum +
                ", commentDetail='" + commentDetail + '\'' +
                ", commentWriteDate=" + commentWriteDate +
                ", commentUpdateDate=" + commentUpdateDate +
                '}';
    }
}
