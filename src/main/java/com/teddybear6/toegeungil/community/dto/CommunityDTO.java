package com.teddybear6.toegeungil.community.dto;

import com.teddybear6.toegeungil.community.entity.Community;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CommunityDTO {

    private int communityNum; // 커뮤니티 번호
    private int userNum; // 회원 번호(작성자)
    private String communityTitle; // 커뮤니티 제목
    private String communityIntro; // 커뮤니티 소개
    private int categoryCode; // 카테고리 번호
    private List<CommunityKeywordDTO> communityKeywordDTOList;// 키워드 번호
    private int localCode; // 지역 번호
    private String communityStatus; // 커뮤니티 상태
    private Date postWriteDate; // 커뮤니티 작성일
    private Date postUpdateDate; // 커뮤니티 수정일

    public CommunityDTO() {
    }

    public CommunityDTO(int communityNum, int userNum, String communityTitle, String communityIntro, int categoryCode, List<CommunityKeywordDTO> communityKeywordDTOList, int localCode, String communityStatus, Date postWriteDate, Date postUpdateDate) {
        this.communityNum = communityNum;
        this.userNum = userNum;
        this.communityTitle = communityTitle;
        this.communityIntro = communityIntro;
        this.categoryCode = categoryCode;
        this.communityKeywordDTOList = communityKeywordDTOList;
        this.localCode = localCode;
        this.communityStatus = communityStatus;
        this.postWriteDate = postWriteDate;
        this.postUpdateDate = postUpdateDate;
    }

    public CommunityDTO(Community community) {
        this.communityNum = community.getCommunityNum();
        this.userNum = community.getUserNum();
        this.communityTitle = community.getCommunityTitle();
        this.communityIntro = community.getCommunityIntro();
        this.categoryCode = community.getCategoryCode();
        this.localCode = community.getLocalCode();
        this.communityStatus = community.getCommunityStatus();
        this.postWriteDate = community.getPostWriteDate();
        this.postUpdateDate = community.getPostUpdateDate();
        this.communityKeywordDTOList = community.getCommunityKeywordList().stream()
                .map(m -> new CommunityKeywordDTO(m.getKeyword()))
                .collect(Collectors.toList());
    }

    public int getCommunityNum() {
        return communityNum;
    }

    public void setCommunityNum(int communityNum) {
        this.communityNum = communityNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public String getCommunityTitle() {
        return communityTitle;
    }

    public void setCommunityTitle(String communityTitle) {
        this.communityTitle = communityTitle;
    }

    public String getCommunityIntro() {
        return communityIntro;
    }

    public void setCommunityIntro(String communityIntro) {
        this.communityIntro = communityIntro;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public List<CommunityKeywordDTO> getCommunityKeywordDTOList() {
        return communityKeywordDTOList;
    }

    public void setCommunityKeywordDTOList(List<CommunityKeywordDTO> communityKeywordDTOList) {
        this.communityKeywordDTOList = communityKeywordDTOList;
    }

    public int getLocalCode() {
        return localCode;
    }

    public void setLocalCode(int localCode) {
        this.localCode = localCode;
    }

    public String getCommunityStatus() {
        return communityStatus;
    }

    public void setCommunityStatus(String communityStatus) {
        this.communityStatus = communityStatus;
    }

    public Date getPostWriteDate() {
        return postWriteDate;
    }

    public void setPostWriteDate(Date postWriteDate) {
        this.postWriteDate = postWriteDate;
    }

    public Date getPostUpdateDate() {
        return postUpdateDate;
    }

    public void setPostUpdateDate(Date postUpdateDate) {
        this.postUpdateDate = postUpdateDate;
    }

    @Override
    public String toString() {
        return "CommunityDTO{" +
                "communityNum=" + communityNum +
                ", userNum=" + userNum +
                ", communityTitle='" + communityTitle + '\'' +
                ", communityIntro='" + communityIntro + '\'' +
                ", categoryCode=" + categoryCode +
                ", communityKeywordDTOList=" + communityKeywordDTOList +
                ", localCode=" + localCode +
                ", communityStatus='" + communityStatus + '\'' +
                ", postWriteDate=" + postWriteDate +
                ", postUpdateDate=" + postUpdateDate +
                '}';
    }
}