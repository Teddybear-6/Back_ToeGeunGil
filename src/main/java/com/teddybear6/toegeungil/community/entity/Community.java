package com.teddybear6.toegeungil.community.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "community")
public class Community {

    @Id
    @Column(name = "community_num") // 커뮤니티 번호
    private int communityNum;
    @Column(name = "community_title") // 커뮤니티 제목
    private String communityTitle;
    @Column(name = "community_intro") // 커뮤니티 소개
    private String communityIntro;
    @Column(name = "category_num") // 카테고리 번호
    private int categoryNum;
    @Column(name = "keyword_num") // 키워드 번호
    private int keywordNum;
    @Column(name = "location_num") // 지역 번호
    private int locationId;
    @Column(name = "community_status") // 커뮤니티 상태
    private String communityStatus;
    @Column(name = "post_write_date") // 커뮤니티 작성일
    private Date postWriteDate;
    @Column(name = "post_update_date") // 커뮤니티 수정일
    private Date postUpdateDate;

    public Community() {
    }

    public Community(int communityNum, String communityTitle, String communityIntro, int categoryNum, int keywordNum, int locationId, String communityStatus, Date postWriteDate, Date postUpdateDate) {a
        this.communityNum = communityNum;
        this.communityTitle = communityTitle;
        this.communityIntro = communityIntro;
        this.categoryNum = categoryNum;
        this.keywordNum = keywordNum;
        this.locationId = locationId;
        this.communityStatus = communityStatus;
        this.postWriteDate = postWriteDate;
        this.postUpdateDate = postUpdateDate;
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

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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
                ", locationId=" + locationId +
                ", communityStatus='" + communityStatus + '\'' +
                ", postWriteDate=" + postWriteDate +
                ", postUpdateDate=" + postUpdateDate +
                '}';
    }
}
