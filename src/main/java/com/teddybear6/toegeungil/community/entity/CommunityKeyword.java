package com.teddybear6.toegeungil.community.entity;

import com.teddybear6.toegeungil.keyword.entity.Keyword;

import javax.persistence.*;

@Entity
@Table(name = "community_keyword")
public class CommunityKeyword {

    @EmbeddedId
    private CommunityPK communityPK;

    @MapsId("communityNum")
    @ManyToOne
    @JoinColumn(name = "community_num")
    private Community community;

    @MapsId("keywordCode")
    @ManyToOne
    @JoinColumn(name = "keyword_code")
    private Keyword keyword;

    public CommunityKeyword() {
    }

    public CommunityKeyword(CommunityPK communityPK, Community community, Keyword keyword) {
        this.communityPK = communityPK;
        this.community = community;
        this.keyword = keyword;
    }

    public CommunityPK getCommunityPK() {
        return communityPK;
    }

    public void setCommunityPK(CommunityPK communityPK) {
        this.communityPK = communityPK;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "CommunityKeyword{" +
                "communityPK=" + communityPK +
                ", keyword=" + keyword +
                '}';
    }
}


