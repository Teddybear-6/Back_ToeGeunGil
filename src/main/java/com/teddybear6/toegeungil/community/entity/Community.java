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
}
