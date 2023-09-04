package com.teddybear6.toegeungil.hobby.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class HobbyGetDTO {
    private int hobbyCode;

    private String hobbyTitle; //제목

    private int tutorCode; //선생 번호

    private String tutorIntro; //선생소개

    private int maxPersonnel;  //최대인원

    private int hobbyPrice;  // 가격

    private String intro; // 시작전 소개


    private Date date; // 일정

    private Date startTime; //시간
    private Date endTime; //시간

    private int categoryCode; // 카테고리

    private String close; //마감
    public HobbyGetDTO() {
    }

    public HobbyGetDTO(int hobbyCode, String hobbyTitle, int tutorCode, String tutorIntro, int maxPersonnel, int hobbyPrice, String intro, Date date, Date startTime, Date endTime, int categoryCode, String close) {
        this.hobbyCode = hobbyCode;
        this.hobbyTitle = hobbyTitle;
        this.tutorCode = tutorCode;
        this.tutorIntro = tutorIntro;
        this.maxPersonnel = maxPersonnel;
        this.hobbyPrice = hobbyPrice;
        this.intro = intro;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.categoryCode = categoryCode;
        this.close = close;
    }

    public int getHobbyCode() {
        return hobbyCode;
    }

    public void setHobbyCode(int hobbyCode) {
        this.hobbyCode = hobbyCode;
    }

    public String getHobbyTitle() {
        return hobbyTitle;
    }

    public void setHobbyTitle(String hobbyTitle) {
        this.hobbyTitle = hobbyTitle;
    }

    public int getTutorCode() {
        return tutorCode;
    }

    public void setTutorCode(int tutorCode) {
        this.tutorCode = tutorCode;
    }

    public String getTutorIntro() {
        return tutorIntro;
    }

    public void setTutorIntro(String tutorIntro) {
        this.tutorIntro = tutorIntro;
    }

    public int getMaxPersonnel() {
        return maxPersonnel;
    }

    public void setMaxPersonnel(int maxPersonnel) {
        this.maxPersonnel = maxPersonnel;
    }

    public int getHobbyPrice() {
        return hobbyPrice;
    }

    public void setHobbyPrice(int hobbyPrice) {
        this.hobbyPrice = hobbyPrice;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    @Override
    public String toString() {
        return "HobbyGetDTO{" +
                "hobbyCode=" + hobbyCode +
                ", hobbyTitle='" + hobbyTitle + '\'' +
                ", tutorCode=" + tutorCode +
                ", tutorIntro='" + tutorIntro + '\'' +
                ", maxPersonnel=" + maxPersonnel +
                ", hobbyPrice=" + hobbyPrice +
                ", intro='" + intro + '\'' +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", categoryCode=" + categoryCode +
                ", close='" + close + '\'' +
                '}';
    }
}
