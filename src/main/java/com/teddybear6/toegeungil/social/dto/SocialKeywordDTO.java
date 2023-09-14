package com.teddybear6.toegeungil.social.dto;

public class SocialKeywordDTO {

    private int keywordCode;

    public SocialKeywordDTO() {
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
