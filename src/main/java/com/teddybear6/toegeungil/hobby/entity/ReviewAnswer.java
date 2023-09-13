package com.teddybear6.toegeungil.hobby.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "hobby_review_answer")
@DynamicInsert
@Where(clause = "review_anwser_status='Y'")
public class ReviewAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "review_answer_code")
    private int reviewAnswerCode;



    @Column(name = "review_code")
    private int reviewCode;




    @Column(name = "content")
    private String content;


    @Column(name = "tutor_code")
    private int tutorCode;

    @Column(name = "review_anwser_status"
            ,columnDefinition = "varchar(1)" )
    @ColumnDefault("'Y'")
    private String reviewAnwseStatus;

    public ReviewAnswer() {
    }


    public ReviewAnswer(int reviewAnswerCode, int reviewCode, String content, int tutorCode) {
        this.reviewAnswerCode = reviewAnswerCode;
        this.reviewCode = reviewCode;
        this.content = content;
        this.tutorCode = tutorCode;
    }

    /*builder*/

    public ReviewAnswer reviewAnswerCode(int reviewAnswerCode){
        this.reviewAnswerCode = reviewAnswerCode;
        return this;
    }

    public ReviewAnswer reviewCode(int reviewCode){
        this.reviewCode = reviewCode;
        return  this;
    }

    public ReviewAnswer content(String content){
        this.content = content;
        return this;
    }

    public ReviewAnswer tutorCode(int tutorCode){
        this.tutorCode = tutorCode;
        return this;
    }

    public ReviewAnswer builder(){return  new ReviewAnswer(reviewAnswerCode,reviewCode,content,tutorCode);}


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
        return "ReviewAnswer{" +
                "reviewAnswerCode=" + reviewAnswerCode +
                ", reviewCode=" + reviewCode +
                ", content='" + content + '\'' +
                ", tutorCode=" + tutorCode +
                '}';
    }
}
