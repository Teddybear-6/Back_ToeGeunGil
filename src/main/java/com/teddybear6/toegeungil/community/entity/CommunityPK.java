package com.teddybear6.toegeungil.community.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CommunityPK implements Serializable {

    @Column(name = "community_num")
    private int communityNum;

    @Column(name = "keyword_code")
    private int keywordCode;

    public CommunityPK() {
    }

    public CommunityPK(int communityNum, int keywordCode) {
        this.communityNum = communityNum;
        this.keywordCode = keywordCode;
    }

    public int getCommunityNum() {
        return communityNum;
    }

    public void setCommunityNum(int communityNum) {
        this.communityNum = communityNum;
    }

    public int getKeywordCode() {
        return keywordCode;
    }

    public void setKeywordCode(int keywordCode) {
        this.keywordCode = keywordCode;
    }

    @Override
    public String toString() {
        return "CommunityPk{" +
                "communityNum=" + communityNum +
                ", keywordNum=" + keywordCode +
                '}';
    }
}
