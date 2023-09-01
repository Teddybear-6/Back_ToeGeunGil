package com.teddybear6.toegeungil.community.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import java.util.Date;

public class Community {

    @Id
    @Column(name = "community_id")
    private int communityId;
    @Column(name = "title")
    private String title;
    @Column(name = "intro")
    private String intro;
    @Column(name = "categoryId")
    private int categoryId;
    @Column(name = "keywordId")
    private int keywordId;
    @Column(name = "locationId")
    private int locationId;
    @Column(name = "status")
    private String status;
    @Column(name = "date")
    private Date date;
}
