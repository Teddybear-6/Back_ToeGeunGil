package com.teddybear6.toegeungil.hobby.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teddybear6.toegeungil.hobby.dto.HobbyDTO;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity(name = "hobby")
@Table(name = "hobby")
@DynamicInsert
@Where(clause = "hobby_status='Y'")
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hobby_code")
    private int hobbyCode;

    @Column(name = "hobby_title")
    private String hobbyTitle; //제목

    @Column(name = "user_code")
    private int tutorCode; //선생 번호

    @Column(name = "max_personnel")
    private int maxPersonnel;  //최대인원

    @Column(name= "hobby_price")
    private int hobbyPrice;  // 가격

    @Column(name = "hobby_intro")
    private String intro; // 시작전 소개


    @Column(name = "local_code")
    private int localCode;


    @Column(name ="closing_date")
    @Temporal(TemporalType.DATE)
    private  Date closingDate;


    @Column(name = "hobby_date")
    @Temporal(TemporalType.DATE)
    private Date date; // 일정

    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    private Date startTime; //시간

    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    private Date endTime; //시간


    @Column(name = "category_code")
    private int categoryCode; // 카테고리

    @Column(name="tutor_intro")
    private String tutorIntro;

    @Column(name = "close", columnDefinition = "varchar(1)")
    @ColumnDefault("'N'")
    private String close;

    @Column(name = "hobby_status",columnDefinition = "varchar(1)")
    @ColumnDefault("'Y'")
    private String hobbyStatus;

    @Column(name = "hobby_place")
    private String hobbyPlace;


    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crateDate;



    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hobby")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<HobbyKeyword> hobbyKeywordList;


    @JoinColumn(name = "hobby_code")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<HobbyImage> hobbyImages;


    @JoinColumn(name = "hobby_code")
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<HobbyReview> hobbyReviews;



    public Hobby() {
    }

    public Hobby(int hobbyCode, String hobbyTitle, int tutorCode, int maxPersonnel, int hobbyPrice, String intro, int localCode, Date date, Date startTime, Date endTime, int categoryCode, String tutorIntro, String close, String hobbyStatus, String hobbyPlace, Date crateDate, Date updateDate, List<HobbyKeyword> hobbyKeywordList, List<HobbyImage> hobbyImages, List<HobbyReview> hobbyReviews) {
        this.hobbyCode = hobbyCode;
        this.hobbyTitle = hobbyTitle;
        this.tutorCode = tutorCode;
        this.maxPersonnel = maxPersonnel;
        this.hobbyPrice = hobbyPrice;
        this.intro = intro;
        this.localCode = localCode;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.categoryCode = categoryCode;
        this.tutorIntro = tutorIntro;
        this.close = close;
        this.hobbyStatus = hobbyStatus;
        this.hobbyPlace = hobbyPlace;
        this.crateDate = crateDate;
        this.updateDate = updateDate;
        this.hobbyKeywordList = hobbyKeywordList;
        this.hobbyImages = hobbyImages;
        this.hobbyReviews = hobbyReviews;

    }

    public Hobby(HobbyDTO hobbyDTO) {
        this.hobbyTitle = hobbyDTO.getHobbyTitle();
        this.tutorCode = hobbyDTO.getTutorCode();
        this.maxPersonnel = hobbyDTO.getMaxPersonnel();
        this.hobbyPrice = hobbyDTO.getHobbyPrice();
        this.intro = hobbyDTO.getIntro();
        this.date = hobbyDTO.getDate();
        this.startTime = hobbyDTO.getStartTime();
        this.endTime = hobbyDTO.getEndTime();
        this.categoryCode = hobbyDTO.getCategoryCode();
        this.tutorIntro = hobbyDTO.getTutorIntro();
        this.close = hobbyDTO.getClose();
        this.localCode= hobbyDTO.getLocalCode();
        this.hobbyPlace =hobbyDTO.getHobbyPlace();
        this.closingDate =hobbyDTO.getClosingDate();
        this.crateDate = hobbyDTO.getCrateDate();
        this.updateDate = hobbyDTO.getUpdateDate();
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

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
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

    public String getTutorIntro() {
        return tutorIntro;
    }

    public void setTutorIntro(String tutorIntro) {
        this.tutorIntro = tutorIntro;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public Date getCrateDate() {
        return crateDate;
    }

    public void setCrateDate(Date crateDate) {
        this.crateDate = crateDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<HobbyKeyword> getHobbyKeywordList() {
        return hobbyKeywordList;
    }

    public void setHobbyKeywordList(List<HobbyKeyword> hobbyKeywordList) {
        this.hobbyKeywordList = hobbyKeywordList;
    }

    public String getHobbyStatus() {
        return hobbyStatus;
    }

    public void setHobbyStatus(String hobbyStatus) {
        this.hobbyStatus = hobbyStatus;
    }

    public int getLocalCode() {
        return localCode;
    }

    public void setLocalCode(int localCode) {
        this.localCode = localCode;
    }

    public List<HobbyImage> getHobbyImages() {
        return hobbyImages;
    }

    public void setHobbyImages(List<HobbyImage> hobbyImages) {
        this.hobbyImages = hobbyImages;
    }

    public List<HobbyReview> getHobbyReviews() {
        return hobbyReviews;
    }

    public void setHobbyReviews(List<HobbyReview> hobbyReviews) {
        this.hobbyReviews = hobbyReviews;
    }

    public String getHobbyPlace() {
        return hobbyPlace;
    }

    public void setHobbyPlace(String hobbyPlace) {
        this.hobbyPlace = hobbyPlace;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "hobbyCode=" + hobbyCode +
                ", hobbyTitle='" + hobbyTitle + '\'' +
                ", tutorCode=" + tutorCode +
                ", maxPersonnel=" + maxPersonnel +
                ", hobbyPrice=" + hobbyPrice +
                ", intro='" + intro + '\'' +
                ", localCode=" + localCode +
                ", closingDate=" + closingDate +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", categoryCode=" + categoryCode +
                ", tutorIntro='" + tutorIntro + '\'' +
                ", close='" + close + '\'' +
                ", hobbyStatus='" + hobbyStatus + '\'' +
                ", hobbyPlace='" + hobbyPlace + '\'' +
                ", crateDate=" + crateDate +
                ", updateDate=" + updateDate +
                ", hobbyKeywordList=" + hobbyKeywordList +
                ", hobbyImages=" + hobbyImages +
                ", hobbyReviews=" + hobbyReviews +
                '}';
    }
}
