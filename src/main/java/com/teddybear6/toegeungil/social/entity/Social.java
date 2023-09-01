package com.teddybear6.toegeungil.social.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "social")
public class Social {

    @Id
    @Column(name = "social_num")
    private int socialNum; //게시글 번호(PK)

    @Column(name = "social_name")
    private String socialName; //게시글 제목

    @Column(name = "social_date")
    private Date socialDate; //모임 일자

    @Column(name = "social_fixed_num")
    private int socialFixedNum; //모임 정원(명)

    @Column(name = "social_start_time")
    private Date socialStartTime; //모임 시작 시간

    @Column(name = "socia_end_time")
    private Date socialEndTime; //모임 종료 시간

    @Column(name = "file_num")
    private int fileNum; //사진 번호(FK)

    @Column(name = "category_code")
    private int categoryCode; //카테고리 번호(FK)

    @Column(name = "keyword_num")
    private int keywordNum; //키워드 번호(FK)

    @Column(name = "area_num")
    private int areaNum; //지역 번호(FK)

    @Column(name = "area_details")
    private String areaDetails; //지역 상세

    @Column(name = "social_intro")
    private String socialIntro; //모임 소개

    @Column(name = "social_other")
    private String socialOther; //모임 기타 사항

    @Column(name = "post_reg_date")
    private Date postRegDate; //게시글 등록일

    @Column(name = "post_modi_date")
    private Date postModiDate; //게시글 수정일

    @Column(name = "social_state")
    private String socialState; //게시글 상태

}
