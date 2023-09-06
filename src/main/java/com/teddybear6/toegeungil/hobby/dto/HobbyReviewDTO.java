package com.teddybear6.toegeungil.hobby.dto;

import java.util.Date;

public class HobbyReviewDTO {


    private int reviewCode;


    private int hobbyCode;


    private int userNo;
    private String content;

    private int score;

    private Date crateDate;

    private Date updateDate;

    public HobbyReviewDTO() {
    }

    public HobbyReviewDTO(int reviewCode, int hobbyCode, int userNo, String content, int score, Date crateDate, Date updateDate) {
        this.reviewCode = reviewCode;
        this.hobbyCode = hobbyCode;
        this.userNo = userNo;
        this.content = content;
        this.score = score;
        this.crateDate = crateDate;
        this.updateDate = updateDate;
    }

    public int getReviewCode() {
        return reviewCode;
    }

    public void setReviewCode(int reviewCode) {
        this.reviewCode = reviewCode;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getCrateDate() {
        return crateDate;
    }

    public void setCrateDate(Date crateDate) {
        this.crateDate = crateDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getHobbyCode() {
        return hobbyCode;
    }

    public void setHobbyCode(int hobbyCode) {
        this.hobbyCode = hobbyCode;
    }

    @Override
    public String toString() {
        return "HobbyReviewDTO{" +
                "reviewCode=" + reviewCode +
                ", hobbyCode=" + hobbyCode +
                ", userNo=" + userNo +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", crateDate=" + crateDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
