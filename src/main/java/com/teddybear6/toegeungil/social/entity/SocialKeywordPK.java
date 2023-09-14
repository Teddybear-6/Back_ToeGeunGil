package com.teddybear6.toegeungil.social.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Embeddable
public class SocialKeywordPK implements Serializable {

    @Column(name = "social_num")
    private int socialNum; //게시글 번호(PK)

    @Column(name = "keyword_code")
    private int keywordCode;

    public SocialKeywordPK() {
    }

    public SocialKeywordPK(int socialNum, int keywordCode) {
        this.socialNum = socialNum;
        this.keywordCode = keywordCode;
    }

    public int getSocialNum() {
        return socialNum;
    }

    public void setSocialNum(int socialNum) {
        this.socialNum = socialNum;
    }

    public int getKeywordCode() {
        return keywordCode;
    }

    public void setKeywordCode(int keywordCode) {
        this.keywordCode = keywordCode;
    }

    @Override
    public String toString() {
        return "SocialKeywordPK{" +
                "socialNum=" + socialNum +
                ", keywordCode=" + keywordCode +
                '}';
    }
}
