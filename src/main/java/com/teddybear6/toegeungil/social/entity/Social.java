package com.teddybear6.toegeungil.social.entity;

import com.teddybear6.toegeungil.social.dto.SocialDTO;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "social") //DB 엑세스 시 테이블 이름을 생성하기 위한 name
public class Social {

    @Id //PK
    @Column(name = "social_num", nullable = false) //NOT NULL
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 관리하는 전략 사용
    private int socialNum; //게시글 번호(PK)

    @Column(name = "user_num", nullable = false) //NOT NULL
    private int userNum; //회원번호(작성자)

    @Column(name = "social_name", nullable = false) //NOT NULL
    private String socialName; //게시글 제목

    @Column(name = "social_date", nullable = false) //NOT NULL
    private Date socialDate; //모임 일자

    @Column(name = "social_fixed_num", nullable = false) //NOT NULL
    private int socialFixedNum; //모임 정원(명)

    @Column(name = "social_start_time", nullable = false) //NOT NULL
    private Date socialStartTime; //모임 시작 시간

    @Column(name = "socia_end_time")
    private Date socialEndTime; //모임 종료 시간

    @Column(name = "file_num", nullable = false) //NOT NULL
    private int fileNum; //사진 번호(FK)

    @Column(name = "category_code", nullable = false) //NOT NULL
    private int categoryCode; //카테고리 번호(FK)

    @Column(name = "keyword_num")
    private int keywordNum; //키워드 번호(FK)

    @Column(name = "area_num", nullable = false) //NOT NULL
    private int areaNum; //지역 번호(FK)

    @Column(name = "area_details")
    private String areaDetails; //지역 상세

    @Column(name = "social_intro", nullable = false) //NOT NULL
    private String socialIntro; //모임 소개

    @Column(name = "social_other")
    private String socialOther; //모임 기타 사항

    @Column(name = "post_reg_date", nullable = false) //NOT NULL
    @Temporal(TemporalType.DATE) //날짜를 저장한다.
    private Date postRegDate; //게시글 등록일

    @Column(name = "post_modi_date")
    private Date postModiDate; //게시글 수정일

    @Column(name = "social_state", nullable = false) //NOT NULL
    @ColumnDefault("Y")
    private String socialState; //게시글 상태

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
        this.fileNum = socialDTO.getFileNum();
        this.categoryCode = socialDTO.getCategoryCode();
        this.keywordNum = socialDTO.getKeywordNum();
        this.areaNum = socialDTO.getAreaNum();
        this.areaDetails = socialDTO.getAreaDetails();
        this.socialIntro = socialDTO.getSocialIntro();
        this.socialOther = socialDTO.getSocialOther();
        this.postRegDate = socialDTO.getPostRegDate();
        this.postModiDate = socialDTO.getPostModiDate();
        this.socialState = socialDTO.getSocialState();
    }

    public Social(int socialNum, int userNum, String socialName, Date socialDate, int socialFixedNum, Date socialStartTime, Date socialEndTime,
                  int fileNum, int categoryCode, int keywordNum, int areaNum, String areaDetails, String socialIntro, String socialOther,
                  Date postRegDate, Date postModiDate, String socialState) {
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
        return "Social{" +
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
