package com.teddybear6.toegeungil.community.entity;

import com.teddybear6.toegeungil.keyword.entity.Keyword;

import javax.persistence.*;

@Entity
@Table(name = "community_keyword")
public class CommunityKeyword {
    @EmbeddedId
    private CommunityPK communityPk;

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

    public CommunityKeyword(CommunityPK communityPk, Community community, Keyword keyword) {
        this.communityPk = communityPk;
        this.community = community;
        this.keyword = keyword;
    }

    public CommunityPK getCommunityPk() {
        return communityPk;
    }

    public void setCommunityPk(CommunityPK communityPk) {
        this.communityPk = communityPk;
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
                "communityPk=" + communityPk +
                ", community=" + community +
                ", keyword=" + keyword +
                '}';
    }
}
