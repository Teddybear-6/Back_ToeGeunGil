package com.teddybear6.toegeungil.social.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teddybear6.toegeungil.social.dto.SocialDTO;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "social")
@DynamicInsert
@Table(name = "social") //Entity와 매핑할 테이블 이름
public class Social {

    @Id //PK
    @Column(name = "social_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY: 기본 키 생성을 테이터베이스에 위임(관리하도록)한다.
    private int socialNum; //게시글 번호(PK)

    @Column(name = "user_num", nullable = false) //NOT NULL
    private int userNum; //회원번호(작성자)

    @Column(name = "social_name", nullable = false) //NOT NULL
    private String socialName; //게시글 제목

    @Column(name = "social_date", nullable = false) //NOT NULL
    @Temporal(TemporalType.DATE)
    private Date socialDate; //모임 일자

    @Column(name = "social_fixed_num", nullable = false) //NOT NULL
    private int socialFixedNum; //모임 정원(명)

    @Column(name = "social_start_time", nullable = false) //NOT NULL
    @Temporal(TemporalType.TIME)
    private Date socialStartTime; //모임 시작 시간

    @Column(name = "socia_end_time")
    @Temporal(TemporalType.TIME)
    private Date socialEndTime; //모임 종료 시간

//    @Column(name = "file_num", nullable = false) //NOT NULL
//    private int fileNum; //사진 번호(FK)

    @Column(name = "category_code", nullable = false) //NOT NULL
    private int categoryCode; //카테고리 번호(FK)

//    @Column(name = "keyword_code")
//    private int keywordCode; //키워드 번호(FK)

    @Column(name = "local_code", nullable = false) //NOT NULL
    private int localCode; //지역 번호(FK)

    @Column(name = "local_details")
    private String localDetails; //지역 상세

    @Column(name = "social_intro", nullable = false) //NOT NULL
    private String socialIntro; //모임 소개

    @Column(name = "social_other")
    private String socialOther; //모임 기타 사항

    @Column(name = "post_reg_date", nullable = false) //NOT NULL
    @Temporal(TemporalType.DATE) //날짜를 저장한다.
    private Date postRegDate; //게시글 등록일

    @Column(name = "post_modi_date")
    @Temporal(TemporalType.DATE)
    private Date postModiDate; //게시글 수정일

    @Column(name = "social_state", columnDefinition = "VARCHAR(1) default 'Y'") //NOT NULL, (columnDefinition = "자료형 default 원하는 값")
    private String socialState; //게시글 상태

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "social")
    private List<SocialKeyword> socialKeywordList;

    public Social() {
    }

    public Social(SocialDTO socialDTO) {
        this.socialNum = socialDTO.getSocialNum();
        this.userNum = socialDTO.getUserNum();
        this.socialName = socialDTO.getSocialName();
        this.socialDate = socialDTO.getSocialDate();
        this.socialFixedNum = socialDTO.getSocialFixedNum();
        this.socialStartTime = socialDTO.getSocialStartTime();
        this.socialEndTime = socialDTO.getSocialEndTime();
//        this.fileNum = socialDTO.getFileNum();
        this.categoryCode = socialDTO.getCategoryCode();
//        this.keywordCode = socialDTO.getKeywordCode();
        this.localCode = socialDTO.getLocalCode();
        this.localDetails = socialDTO.getLocalDetails();
        this.socialIntro = socialDTO.getSocialIntro();
        this.socialOther = socialDTO.getSocialOther();
        this.postRegDate = socialDTO.getPostRegDate();
        this.postModiDate = socialDTO.getPostModiDate();
        this.socialState = socialDTO.getSocialState();
    }

    public Social(int socialNum, int userNum, String socialName, Date socialDate, int socialFixedNum, Date socialStartTime, Date socialEndTime,
                  int categoryCode, int localCode, String localDetails, String socialIntro, String socialOther,
                  Date postRegDate, Date postModiDate, String socialState, List<SocialKeyword> socialKeywordList) {
        this.socialNum = socialNum;
        this.userNum = userNum;
        this.socialName = socialName;
        this.socialDate = socialDate;
        this.socialFixedNum = socialFixedNum;
        this.socialStartTime = socialStartTime;
        this.socialEndTime = socialEndTime;
//        this.fileNum = fileNum;
        this.categoryCode = categoryCode;
//        this.keywordCode = keywordCode;
        this.localCode = localCode;
        this.localDetails = localDetails;
        this.socialIntro = socialIntro;
        this.socialOther = socialOther;
        this.postRegDate = postRegDate;
        this.postModiDate = postModiDate;
        this.socialState = socialState;
        this.socialKeywordList = socialKeywordList;
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

//    public int getFileNum() {
//        return fileNum;
//    }

//    public void setFileNum(int fileNum) {
//        this.fileNum = fileNum;
//    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

//    public int getKeywordCode() {
//        return keywordCode;
//    }

//    public void setKeywordCode(int keywordCode) {
//        this.keywordCode = keywordCode;
//    }

    public int getLocalCode() {
        return localCode;
    }

    public void setLocalCode(int localCode) {
        this.localCode = localCode;
    }

    public String getLocalDetails() {
        return localDetails;
    }

    public void setLocalDetails(String localDetails) {
        this.localDetails = localDetails;
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

    public List<SocialKeyword> getSocialKeywordList() {
        return socialKeywordList;
    }

    public void setSocialKeywordList(List<SocialKeyword> socialKeywordList) {
        this.socialKeywordList = socialKeywordList;
    }

    @Override
    public String toString() {
        return "Social{" +
                "socialNum=" + socialNum +
                ", userNum=" + userNum +
                ", socialName='" + socialName + '\'' +
                ", socialDate=" + socialDate +
                ", socialFixedNum=" + socialFixedNum +
                ", socialStartTime=" + socialStartTime +
                ", socialEndTime=" + socialEndTime +
//                ", fileNum=" + fileNum +
                ", categoryCode=" + categoryCode +
//                ", keywordCode=" + keywordCode +
                ", localCode=" + localCode +
                ", localDetails='" + localDetails + '\'' +
                ", socialIntro='" + socialIntro + '\'' +
                ", socialOther='" + socialOther + '\'' +
                ", postRegDate=" + postRegDate +
                ", postModiDate=" + postModiDate +
                ", socialState='" + socialState + '\'' +
                ", socialKeywordList=" + socialKeywordList +
                '}';
    }
}
