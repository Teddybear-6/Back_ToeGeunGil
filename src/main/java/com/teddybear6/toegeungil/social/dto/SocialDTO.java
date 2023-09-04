package com.teddybear6.toegeungil.social.dto;

import com.teddybear6.toegeungil.social.entity.Social;

import javax.persistence.Id;
import java.util.Date;

public class SocialDTO {

    private int socialNum; //게시글 번호(PK)

    private int userNum; //회원번호(작성자)

    private String socialName; //게시글 제목

    private Date socialDate; //모임 일자

    private int socialFixedNum; //모임 정원(명)

    private Date socialStartTime; //모임 시작 시간

    private Date socialEndTime; //모임 종료 시간

    private int fileNum; //사진 번호(FK)

    private int categoryCode; //카테고리 번호(FK)

    private int keywordNum; //키워드 번호(FK)

    private int areaNum; //지역 번호(FK)

    private String areaDetails; //지역 상세

    private String socialIntro; //모임 소개

    private String socialOther; //모임 기타 사항

    private Date postRegDate; //게시글 등록일

    private Date postModiDate; //게시글 수정일

    private String socialState; //게시글 상태

    public SocialDTO() {
    }

    public SocialDTO(Social social) {
        //Social Entity를 SocialDTO에 한 번에 담아주기 위해 생성
        this.socialNum = social.getSocialNum();
        this.userNum = social.getUserNum();
        this.socialName = social.getSocialName();
        this.socialDate = social.getSocialDate();
        this.socialFixedNum = social.getSocialFixedNum();
        this.socialStartTime = social.getSocialStartTime();
        this.socialEndTime = social.getSocialEndTime();
        this.fileNum = social.getFileNum();
        this.categoryCode = social.getCategoryCode();
        this.keywordNum = social.getKeywordNum();
        this.areaNum = social.getAreaNum();
        this.areaDetails = social.getAreaDetails();
        this.socialIntro = social.getSocialIntro();
        this.socialOther = social.getSocialOther();
        this.postRegDate = social.getPostRegDate();
        this.postModiDate = social.getPostModiDate();
        this.socialState = social.getSocialState();
    }

    public SocialDTO(int socialNum, int userNum, String socialName, Date socialDate, int socialFixedNum,
                     Date socialStartTime, Date socialEndTime, int fileNum, int categoryCode, int keywordNum, int areaNum,
                     String areaDetails, String socialIntro, String socialOther, Date postRegDate, Date postModiDate, String socialState) {
        this.socialNum = socialNum;
        this.userNum = userNum;
        this.socialName = socialName;
        this.socialDate = socialDate;
        this.socialFixedNum = socialFixedNum;
        this.socialStartTime = socialStartTime;
        this.socialEndTime = socialEndTime;
        this.fileNum = fileNum;
        this.categoryCode = categoryCode;
        this.keywordNum = keywordNum;
        this.areaNum = areaNum;
        this.areaDetails = areaDetails;
        this.socialIntro = socialIntro;
        this.socialOther = socialOther;
        this.postRegDate = postRegDate;
        this.postModiDate = postModiDate;
        this.socialState = socialState;
    }

    public int getSocialNum() {
        return socialNum;
    }

    public void setSocialNum(int socialNum) {
        this.socialNum = socialNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public String getSocialName() {
        return socialName;
    }

    public void setSocialName(String socialName) {
        this.socialName = socialName;
    }

    public Date getSocialDate() {
        return socialDate;
    }

    public void setSocialDate(Date socialDate) {
        this.socialDate = socialDate;
    }

    public int getSocialFixedNum() {
        return socialFixedNum;
    }

    public void setSocialFixedNum(int socialFixedNum) {
        this.socialFixedNum = socialFixedNum;
    }

    public Date getSocialStartTime() {
        return socialStartTime;
    }

    public void setSocialStartTime(Date socialStartTime) {
        this.socialStartTime = socialStartTime;
    }

    public Date getSocialEndTime() {
        return socialEndTime;
    }

    public void setSocialEndTime(Date socialEndTime) {
        this.socialEndTime = socialEndTime;
    }

    public int getFileNum() {
        return fileNum;
    }

    public void setFileNum(int fileNum) {
        this.fileNum = fileNum;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public int getKeywordNum() {
        return keywordNum;
    }

    public void setKeywordNum(int keywordNum) {
        this.keywordNum = keywordNum;
    }

    public int getAreaNum() {
        return areaNum;
    }

    public void setAreaNum(int areaNum) {
        this.areaNum = areaNum;
    }

    public String getAreaDetails() {
        return areaDetails;
    }

    public void setAreaDetails(String areaDetails) {
        this.areaDetails = areaDetails;
    }

    public String getSocialIntro() {
        return socialIntro;
    }

    public void setSocialIntro(String socialIntro) {
        this.socialIntro = socialIntro;
    }

    public String getSocialOther() {
        return socialOther;
    }

    public void setSocialOther(String socialOther) {
        this.socialOther = socialOther;
    }

    public Date getPostRegDate() {
        return postRegDate;
    }

    public void setPostRegDate(Date postRegDate) {
        this.postRegDate = postRegDate;
    }

    public Date getPostModiDate() {
        return postModiDate;
    }

    public void setPostModiDate(Date postModiDate) {
        this.postModiDate = postModiDate;
    }

    public String getSocialState() {
        return socialState;
    }

    public void setSocialState(String socialState) {
        this.socialState = socialState;
    }

    @Override
    public String toString() {
        return "SocialDTO{" +
                "socialNum=" + socialNum +
                ", userNum=" + userNum +
                ", socialName='" + socialName + '\'' +
                ", socialDate=" + socialDate +
                ", socialFixedNum=" + socialFixedNum +
                ", socialStartTime=" + socialStartTime +
                ", socialEndTime=" + socialEndTime +
                ", fileNum=" + fileNum +
                ", categoryCode=" + categoryCode +
                ", keywordNum=" + keywordNum +
                ", areaNum=" + areaNum +
                ", areaDetails='" + areaDetails + '\'' +
                ", socialIntro='" + socialIntro + '\'' +
                ", socialOther='" + socialOther + '\'' +
                ", postRegDate=" + postRegDate +
                ", postModiDate=" + postModiDate +
                ", socialState='" + socialState + '\'' +
                '}';
    }
}
