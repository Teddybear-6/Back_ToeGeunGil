package com.teddybear6.toegeungil.social.dto;

import com.teddybear6.toegeungil.keyword.entity.Keyword;

public class SocialKeywordDTO {

    private int keywordCode;

    public SocialKeywordDTO() {
    }

    public SocialKeywordDTO(Keyword keyword) {
        this.keywordCode = keyword.getKeywordCode();
    }

    public SocialKeywordDTO(int keywordCode) {
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
        return "SocialKeywordDTO{" +
                "keywordCode=" + keywordCode +
                '}';
    }
}
