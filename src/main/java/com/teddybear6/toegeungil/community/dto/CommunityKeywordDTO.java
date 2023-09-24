package com.teddybear6.toegeungil.community.dto;

import com.teddybear6.toegeungil.keyword.entity.Keyword;

public class CommunityKeywordDTO {
    private int keywordCode;

    public CommunityKeywordDTO() {
    }

    public CommunityKeywordDTO(Keyword keyword) {
        this.keywordCode = keyword.getKeywordCode();
    }

    public CommunityKeywordDTO(int keywordCode) {
        this.keywordCode = keywordCode;
    }

    public int getKeywordCode() {
        return keywordCode;
    }

    public void setKeywordCode(int keywordCode) {
        this.keywordCode = keywordCode;
    }

    @Override
    public String toString() {
        return "CommunityKeywordDTO{" +
                "keywordCode=" + keywordCode +
                '}';
    }
}