package com.teddybear6.toegeungil.hobby.entity;


import com.teddybear6.toegeungil.hobby.dto.HobbyReviewDTO;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "hobby_review")
@Table(name = "hobby_review")
public class HobbyReview {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reciew_code")
    private int reviewCode;   //코드


    @Column(name = "hobby_code")
    private int hobbyCode;

    @Column(name = "user_no")
    private int userNo;       //회원번호


    @Column(name = "content")
    private String content;  //내용


    @Column(name = "score")
    private int score;      // 점수

    @Column(name = "create_date")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date crateDate;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateDate;


    public HobbyReview() {
    }

    public HobbyReview(int reviewCode, int hobbyCode, int userNo, String content, int score, Date crateDate, Date updateDate) {
        this.reviewCode = reviewCode;
        this.hobbyCode = hobbyCode;
        this.userNo = userNo;
        this.content = content;
        this.score = score;
        this.crateDate = crateDate;
        this.updateDate = updateDate;
    }


    public HobbyReview(HobbyReviewDTO hobbyReviewDTO) {
        this.hobbyCode = hobbyReviewDTO.getHobbyCode();
        this.userNo = hobbyReviewDTO.getUserNo();
        this.content = hobbyReviewDTO.getContent();
        this.score = hobbyReviewDTO.getScore();
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
        return "HobbyReview{" +
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
