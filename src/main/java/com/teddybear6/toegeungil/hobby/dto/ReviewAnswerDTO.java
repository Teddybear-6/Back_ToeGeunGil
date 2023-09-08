package com.teddybear6.toegeungil.hobby.dto;

import com.teddybear6.toegeungil.hobby.entity.ReviewAnswer;

public class ReviewAnswerDTO {

  private int reviewAnswerCode;

  private int reviewCode;

  private String content;

  private int tutorCode;

    public ReviewAnswerDTO() {
    }

    public ReviewAnswerDTO(int reviewAnswerCode, int reviewCode, String content, int tutorCode) {
        this.reviewAnswerCode = reviewAnswerCode;
        this.reviewCode = reviewCode;
        this.content = content;
        this.tutorCode = tutorCode;
    }

    public ReviewAnswerDTO reviewAnswerCode(int reviewAnswerCode){
        this.reviewAnswerCode = reviewAnswerCode;
        return this;
    }

    public ReviewAnswerDTO reviewCode(int reviewCode){
        this.reviewCode = reviewCode;
        return  this;
    }

    public ReviewAnswerDTO content(String content){
        this.content = content;
        return this;
    }

    public ReviewAnswerDTO tutorCode(int tutorCode){
        this.tutorCode = tutorCode;
        return this;
    }



    public ReviewAnswerDTO builder(){return  new ReviewAnswerDTO(reviewAnswerCode,reviewCode,content,tutorCode);}

    public int getReviewAnswerCode() {
        return reviewAnswerCode;
    }

    public void setReviewAnswerCode(int reviewAnswerCode) {
        this.reviewAnswerCode = reviewAnswerCode;
    }

    public int getReviewCode() {
        return reviewCode;
    }

    public void setReviewCode(int reviewCode) {
        this.reviewCode = reviewCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTutorCode() {
        return tutorCode;
    }

    public void setTutorCode(int tutorCode) {
        this.tutorCode = tutorCode;
    }

    @Override
    public String toString() {
        return "ReviewAnswerDTO{" +
                "reviewAnswerCode=" + reviewAnswerCode +
                ", reviewCode=" + reviewCode +
                ", content='" + content + '\'' +
                ", tutorCode=" + tutorCode +
                '}';
    }
}
