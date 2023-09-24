package com.teddybear6.toegeungil.community.dto;

import com.teddybear6.toegeungil.community.entity.Comment;

import java.time.LocalDateTime;
import java.util.Date;

public class CommentDTO {

    private int commentNum; // 댓글 번호
    private int userNum; // 회원 번호
    private int communityNum; // 커뮤니티 번호
    private String commentDetail; // 댓글 내용
    private Date commentWriteDate; // 댓글 작성 날짜
    private Date commentUpdateDate; // 댓글 수정 날짜

    public CommentDTO() {
    }

    public CommentDTO(int commentNum, int userNum, int communityNum, String commentDetail, Date commentWriteDate, Date commentUpdateDate) {
        this.commentNum = commentNum;
        this.userNum = userNum;
        this.communityNum = communityNum;
        this.commentDetail = commentDetail;
        this.commentWriteDate = commentWriteDate;
        this.commentUpdateDate = commentUpdateDate;
    }

    public CommentDTO(Comment comment) {
        this.commentNum = comment.getCommentNum();
        this.userNum = comment.getUserNum();
        this.communityNum = comment.getCommunityNum();
        this.commentDetail = comment.getCommentDetail();
        this.commentWriteDate = comment.getCommentWriteDate();
        this.commentUpdateDate = comment.getCommentUpdateDate();
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
        return "CommentDTO{" +
                "commentNum=" + commentNum +
                ", userNum=" + userNum +
                ", communityNum=" + communityNum +
                ", commentDetail='" + commentDetail + '\'' +
                ", commentWriteDate=" + commentWriteDate +
                ", commentUpdateDate=" + commentUpdateDate +
                '}';
    }
}
