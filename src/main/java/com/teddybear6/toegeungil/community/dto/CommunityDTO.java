package com.teddybear6.toegeungil.community.dto;

import com.teddybear6.toegeungil.community.entity.Community;

import java.util.Date;
import java.util.List;

public class CommunityDTO {

    private int communityNum; // 커뮤니티 번호
    private int userNum; // 회원 번호(작성자)
    private String communityTitle; // 커뮤니티 제목
    private String communityIntro; // 커뮤니티 소개
    private int categoryNum; // 카테고리 번호
    private List<CommunityKeywordDTO> communityKeywordDTOList;// 키워드 번호
    private int locationNum; // 지역 번호
    private String communityStatus; // 커뮤니티 상태
    private Date postWriteDate; // 커뮤니티 작성일
    private Date postUpdateDate; // 커뮤니티 수정일

    public CommunityDTO() {
    }

    public CommunityDTO(int communityNum, int userNum, String communityTitle, String communityIntro, int categoryNum, List<CommunityKeywordDTO> communityKeywordDTOList, int locationNum, String communityStatus, Date postWriteDate, Date postUpdateDate) {
        this.communityNum = communityNum;
        this.userNum = userNum;
        this.communityTitle = communityTitle;
        this.communityIntro = communityIntro;
        this.categoryNum = categoryNum;
        this.communityKeywordDTOList = communityKeywordDTOList;
        this.locationNum = locationNum;
        this.communityStatus = communityStatus;
        this.postWriteDate = postWriteDate;
        this.postUpdateDate = postUpdateDate;
    }

    public CommunityDTO(Community community) {
        this.communityNum = community.getCommunityNum();
        this.userNum = community.getUserNum() == null? 0 : community.getUserNum();
        this.communityTitle = community.getCommunityTitle();
        this.communityIntro = community.getCommunityIntro();
        this.categoryNum = community.getCategoryNum();
        this.locationNum = community.getLocationNum();
        this.communityStatus = community.getCommunityStatus();
        this.postWriteDate = community.getPostWriteDate();
        this.postUpdateDate = community.getPostUpdateDate();
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

    public int getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(int categoryNum) {
        this.categoryNum = categoryNum;
    }

    public List<CommunityKeywordDTO> getCommunityKeywordDTOList() {
        return communityKeywordDTOList;
    }

    public void setCommunityKeywordDTOList(List<CommunityKeywordDTO> communityKeywordDTOList) {
        this.communityKeywordDTOList = communityKeywordDTOList;
    }

    public int getLocationNum() {
        return locationNum;
    }

    public void setLocationNum(int locationNum) {
        this.locationNum = locationNum;
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
                ", categoryNum=" + categoryNum +
                ", communityKeywordDTOList=" + communityKeywordDTOList +
                ", locationNum=" + locationNum +
                ", communityStatus='" + communityStatus + '\'' +
                ", postWriteDate=" + postWriteDate +
                ", postUpdateDate=" + postUpdateDate +
                '}';
    }
}
