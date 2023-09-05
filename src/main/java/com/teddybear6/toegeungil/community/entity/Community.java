package com.teddybear6.toegeungil.community.entity;


import com.teddybear6.toegeungil.community.dto.CommunityDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "community")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_num") // 커뮤니티 번호
    private int communityNum;
    @Column(name = "community_title", nullable = false) // 커뮤니티 제목
    private String communityTitle;
    @Column(name = "community_intro", nullable = false) // 커뮤니티 소개
    private String communityIntro;
    @Column(name = "category_num", nullable = false) // 카테고리 번호
    private int categoryNum;
    @Column(name = "keyword_num", nullable = false) // 키워드 번호
    private int keywordNum;
    @Column(name = "location_num", nullable = false) // 지역 번호
    private int locationNum;
    @Column(name = "community_status") // 커뮤니티 상태
    private String communityStatus;
    @Column(name = "post_write_date") // 커뮤니티 작성일
    @Temporal(TemporalType.DATE)
    private Date postWriteDate;
    @Column(name = "post_update_date") // 커뮤니티 수정일
    @Temporal(TemporalType.DATE)
    private Date postUpdateDate;

    public Community() {
    }

    public Community(int communityNum, String communityTitle, String communityIntro, int categoryNum, int keywordNum, int locationNum, String communityStatus, Date postWriteDate, Date postUpdateDate) {
        this.communityNum = communityNum;
        this.communityTitle = communityTitle;
        this.communityIntro = communityIntro;
        this.categoryNum = categoryNum;
        this.keywordNum = keywordNum;
        this.locationNum = locationNum;
        this.communityStatus = communityStatus;
        this.postWriteDate = postWriteDate;
        this.postUpdateDate = postUpdateDate;
    }

    public Community(CommunityDTO communityDTO) {
        this.communityTitle = communityDTO.getCommunityTitle();
        this.communityIntro = communityDTO.getCommunityIntro();
        this.categoryNum = communityDTO.getCategoryNum();
        this.keywordNum = communityDTO.getKeywordNum();
        this.locationNum = communityDTO.getLocationNum();
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

    public int getKeywordNum() {
        return keywordNum;
    }

    public void setKeywordNum(int keywordNum) {
        this.keywordNum = keywordNum;
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
        return "Community{" +
                "communityNum=" + communityNum +
                ", communityTitle='" + communityTitle + '\'' +
                ", communityIntro='" + communityIntro + '\'' +
                ", categoryNum=" + categoryNum +
                ", keywordNum=" + keywordNum +
                ", locationNum=" + locationNum +
                ", communityStatus='" + communityStatus + '\'' +
                ", postWriteDate=" + postWriteDate +
                ", postUpdateDate=" + postUpdateDate +
                '}';
    }
}
