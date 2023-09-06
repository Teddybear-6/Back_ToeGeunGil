package com.teddybear6.toegeungil.qna.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "qna_answer")
public class Answer {
    //답변 엔티티
    @Id
    @Column(name = "ansNum")
    private int answerNum;                      //답변 번호

    @Column(name = "ansTitle")
    private String answerTitle;                 //답변 제목
    @Column(name = "ansContent")
    private String answerContent;               //답변 내용

    @Column(name = "ansNick")
    private String answerNick;                  //답변자 닉네임

    @Column(name = "ansDate")
    private Date answerDate;                    //답변 생성일

    @Column(name = "ansDelete")
    private Date answerDelete;                  //답변 삭제일

    @Column(name = "ansStatus")
    private String answerStatus;                //답변 상태

    public Answer() {
    }

    public Answer(int answerNum, String answerTitle, String answerContent, String answerNick, Date answerDate, Date answerDelete, String answerStatus) {
        this.answerNum = answerNum;
        this.answerTitle = answerTitle;
        this.answerContent = answerContent;
        this.answerNick = answerNick;
        this.answerDate = answerDate;
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

    @Override
    public String  toString() {
        return "Answer{" +
                "answerNum=" + answerNum +
                ", answerTitle='" + answerTitle + '\'' +
                ", answerContent='" + answerContent + '\'' +
                ", answerNick='" + answerNick + '\'' +
                ", answerDate=" + answerDate +
                ", answerDelete=" + answerDelete +
                ", answerStatus='" + answerStatus + '\'' +
                '}';
    }
}
