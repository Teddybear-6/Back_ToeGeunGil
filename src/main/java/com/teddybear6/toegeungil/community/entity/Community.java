package com.teddybear6.toegeungil.community.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teddybear6.toegeungil.community.dto.CommunityDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "community")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_num") // 커뮤니티 번호
    private int communityNum;
    @Column(name = "user_num")
    private Integer userNum; // 회원 번호(작성자)
    @Column(name = "community_title", nullable = false) // 커뮤니티 제목
    private String communityTitle;
    @Column(name = "community_intro", nullable = false) // 커뮤니티 소개
    private String communityIntro;
    @Column(name = "category_code", nullable = false) // 카테고리 번호
    private int categoryCode;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "community")
    private List<CommunityKeyword> communityKeywordList;

    @Column(name = "local_code", nullable = false) // 지역 번호
    private int localCode;
    @Column(name = "community_status") // 커뮤니티 상태
    private String communityStatus;
    @Column(name = "post_write_date") // 커뮤니티 작성일
    @Temporal(TemporalType.TIMESTAMP)
    private Date postWriteDate;
    @Column(name = "post_update_date") // 커뮤니티 수정일
    @Temporal(TemporalType.TIMESTAMP)
    private Date postUpdateDate;

    public Community() {
    }

    public Community(int communityNum, Integer userNum, String communityTitle, String communityIntro, int categoryCode, List<CommunityKeyword> communityKeywordList, int localCode, String communityStatus, Date postWriteDate, Date postUpdateDate) {
        this.communityNum = communityNum;
        this.userNum = userNum;
        this.communityTitle = communityTitle;
        this.communityIntro = communityIntro;
        this.categoryCode = categoryCode;
        this.communityKeywordList = communityKeywordList;
        this.localCode = localCode;
        this.communityStatus = communityStatus;
        this.postWriteDate = postWriteDate;
        this.postUpdateDate = postUpdateDate;
    }

    public Community(CommunityDTO communityDTO) {
        this.userNum = communityDTO.getUserNum();
        this.communityTitle = communityDTO.getCommunityTitle();
        this.communityIntro = communityDTO.getCommunityIntro();
        this.categoryCode = communityDTO.getCategoryCode();
        this.localCode = communityDTO.getLocalCode();
        this.communityStatus = communityDTO.getCommunityStatus();
        this.postWriteDate = communityDTO.getPostWriteDate();
        this.postUpdateDate = communityDTO.getPostUpdateDate();
    }

    public int getCommunityNum() {
        return communityNum;
    }

    public void setCommunityNum(int communityNum) {
        this.communityNum = communityNum;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
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

    public List<CommunityKeyword> getCommunityKeywordList() {
        return communityKeywordList;
    }

    public void setCommunityKeywordList(List<CommunityKeyword> communityKeywordList) {
        this.communityKeywordList = communityKeywordList;
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
        return "Community{" +
                "communityNum=" + communityNum +
                ", userNum=" + userNum +
                ", communityTitle='" + communityTitle + '\'' +
                ", communityIntro='" + communityIntro + '\'' +
                ", categoryCode=" + categoryCode +
                ", communityKeywordList=" + communityKeywordList +
                ", localCode=" + localCode +
                ", communityStatus='" + communityStatus + '\'' +
                ", postWriteDate=" + postWriteDate +
                ", postUpdateDate=" + postUpdateDate +
                '}';
    }
}