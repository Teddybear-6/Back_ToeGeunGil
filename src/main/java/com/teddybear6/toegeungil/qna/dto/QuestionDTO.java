package com.teddybear6.toegeungil.qna.dto;

import java.util.Date;

public class QuestionDTO {
    private int questionNum;                            //질문 번호

    private String questionTitle;                       //질문 타이틀

    private String questionContent;                     //질문 내용

    private String questionNick;                        //질문 작성자

    private Date questionDate;                          //질문 생성일

    private Date questionUpdate;                        //질문 수정일

    private Date questionDelete;                        //질문 삭제일

    private String questionStatus;                      //질문 상태

    public QuestionDTO() {
    }

    public QuestionDTO(int questionNum, String questionTitle, String questionContent, String questionNick, Date questionDate, Date questionUpdate, Date questionDelete, String questionStatus) {
        this.questionNum = questionNum;
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
        this.questionNick = questionNick;
        this.questionDate = questionDate;
        this.questionUpdate = questionUpdate;
        this.questionDelete = questionDelete;
        this.questionStatus = questionStatus;
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

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "questionNum=" + questionNum +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", questionNick='" + questionNick + '\'' +
                ", questionDate=" + questionDate +
                ", questionUpdate=" + questionUpdate +
                ", questionDelete=" + questionDelete +
                ", questionStatus='" + questionStatus + '\'' +
                '}';
    }
}
