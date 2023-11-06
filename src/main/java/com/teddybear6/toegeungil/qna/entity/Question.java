package com.teddybear6.toegeungil.qna.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "qna_question")
@Table(name = "qna_question")
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "que_status='Y'")
public class Question {
    //질문 관련 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //기본키 1씩 증가
    @Column(name = "queNum")
    private int questionNum;                            //질문 번호
    @Column(name = "queTitle")
    private String questionTitle;                       //질문 타이틀
    @Column(name = "queContent")
    private String questionContent;                     //질문 내용
    @Column(name = "queNickName")
    private String questionNick;                        //질문 작성자

    @Column(name ="userNo")
    private String userNo;


    @Column(name = "queDate")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date questionDate;                          //질문 생성일
    @Column(name = "queUpdate")
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date questionUpdate;                        //질문 수정일
    @Column(name = "queDelete")
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date questionDelete;                        //질문 삭제일
    @Column(name = "que_status",columnDefinition = "varchar(1)")
    private String questionStatus;                      //질문 상태

   @Column(name = "answer_status" ,columnDefinition = "varchar(1)")   //답변여부
   private String answerStatus;

    public Question() {
    }

    public Question(int questionNum, String questionTitle, String questionContent, String questionNick, String userNo, Date questionDate, Date questionUpdate, Date questionDelete, String questionStatus, String answerStatus) {
        this.questionNum = questionNum;
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
        this.questionNick = questionNick;
        this.userNo = userNo;
        this.questionDate = questionDate;
        this.questionUpdate = questionUpdate;
        this.questionDelete = questionDelete;
        this.questionStatus = questionStatus;
        this.answerStatus = answerStatus;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionNick() {
        return questionNick;
    }

    public void setQuestionNick(String questionNick) {
        this.questionNick = questionNick;
    }

    public Date getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(Date questionDate) {
        this.questionDate = questionDate;
    }

    public Date getQuestionUpdate() {
        return questionUpdate;
    }

    public void setQuestionUpdate(Date questionUpdate) {
        this.questionUpdate = questionUpdate;
    }

    public Date getQuestionDelete() {
        return questionDelete;
    }

    public void setQuestionDelete(Date questionDelete) {
        this.questionDelete = questionDelete;
    }

    public String getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(String answerStatus) {
        this.answerStatus = answerStatus;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionNum=" + questionNum +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", questionNick='" + questionNick + '\'' +
                ", userNo='" + userNo + '\'' +
                ", questionDate=" + questionDate +
                ", questionUpdate=" + questionUpdate +
                ", questionDelete=" + questionDelete +
                ", questionStatus='" + questionStatus + '\'' +
                ", answerStatus='" + answerStatus + '\'' +
                '}';
    }
}
