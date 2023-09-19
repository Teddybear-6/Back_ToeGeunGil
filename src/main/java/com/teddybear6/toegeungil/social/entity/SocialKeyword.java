package com.teddybear6.toegeungil.social.entity;

import com.teddybear6.toegeungil.keyword.entity.Keyword;

import javax.persistence.*;

@Entity
@Table(name = "social_keyword")
public class SocialKeyword {

    @EmbeddedId
    private SocialKeywordPK socialKeywordPK;

    @MapsId("socialNum")
    @ManyToOne
    @JoinColumn(name = "social_num")
    private Social social;

    @MapsId("keywordCode")
    @ManyToOne
    @JoinColumn(name = "keyword_code")
    private Keyword keyword;

    public SocialKeyword() {
    }

    public SocialKeyword(SocialKeywordPK socialKeywordPK, Social social, Keyword keyword) {
        this.socialKeywordPK = socialKeywordPK;
        this.social = social;
        this.keyword = keyword;
    }

    public SocialKeywordPK getSocialKeywordPK() {
        return socialKeywordPK;
    }

    public void setSocialKeywordPK(SocialKeywordPK socialKeywordPK) {
        this.socialKeywordPK = socialKeywordPK;
    }

    public Social getSocial() {
        return social;
    }

    public void setSocial(Social social) {
        this.social = social;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "SocialKeyword{" +
                "socialKeywordPK=" + socialKeywordPK +
                ", social=" + social +
                ", keyword=" + keyword +
                '}';
    }
}
