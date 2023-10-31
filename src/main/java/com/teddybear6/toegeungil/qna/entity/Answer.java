package com.teddybear6.toegeungil.qna.entity;

import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "qna_answer")
@Where(clause = "answerStatus='Y'")
public class Answer {
    //답변 엔티티
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //기본키 1씩 증가
    @Column(name = "ansNum")
    private int answerNum;                      //답변 번호

    private int questionNum;                  //질문번호

    @Column(name = "ansTitle")
    private String answerTitle;                 //답변 제목
    @Column(name = "ansContent")
    private String answerContent;               //답변 내용

    @Column(name = "ansNick")
    private String answerNick;                  //답변자 닉네임

    @Column(name = "ansDate")
    @Temporal(TemporalType.DATE)
    private Date answerDate;                  //답변 생성일
    @Column(name = "ansUpdate")
    @Temporal(TemporalType.DATE)
    private Date answerUpdate;

    @Column(name = "ansDelete")
    @Temporal(TemporalType.DATE)
    private Date answerDelete;                  //답변 삭제일

    @Column(name = "ansStatus",columnDefinition = "varchar(1)")
    private String answerStatus;                //답변 상태

    public Answer() {
    }

    public Answer(int answerNum, int questionNum, String answerTitle, String answerContent, String answerNick, Date answerDate, Date answerUpdate, Date answerDelete, String answerStatus) {
        this.answerNum = answerNum;
        this.questionNum = questionNum;
        this.answerTitle = answerTitle;
        this.answerContent = answerContent;
        this.answerNick = answerNick;
        this.answerDate = answerDate;
        this.answerUpdate = answerUpdate;
        this.answerDelete = answerDelete;
        this.answerStatus = answerStatus;
    }

    public int getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(int answerNum) {
        this.answerNum = answerNum;
    }

    public String getAnswerTitle() {
        return answerTitle;
    }

    public void setAnswerTitle(String answerTitle) {
        this.answerTitle = answerTitle;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getAnswerNick() {
        return answerNick;
    }

    public void setAnswerNick(String answerNick) {
        this.answerNick = answerNick;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public Date getAnswerDelete() {
        return answerDelete;
    }

    public void setAnswerDelete(Date answerDelete) {
        this.answerDelete = answerDelete;
    }

    public String getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(String answerStatus) {
        this.answerStatus = answerStatus;
    }

    public Date getAnswerUpdate() {
        return answerUpdate;
    }

    public void setAnswerUpdate(Date answerUpdate) {
        this.answerUpdate = answerUpdate;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerNum=" + answerNum +
                ", questionNum=" + questionNum +
                ", answerTitle='" + answerTitle + '\'' +
                ", answerContent='" + answerContent + '\'' +
                ", answerNick='" + answerNick + '\'' +
                ", answerDate=" + answerDate +
                ", answerUpdate=" + answerUpdate +
                ", answerDelete=" + answerDelete +
                ", answerStatus='" + answerStatus + '\'' +
                '}';
    }
}
