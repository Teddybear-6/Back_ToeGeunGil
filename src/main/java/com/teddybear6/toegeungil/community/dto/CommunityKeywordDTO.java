package com.teddybear6.toegeungil.community.dto;

public class CommunityKeywordDTO {

    private int keywordCode;
    private String keywordName;

    public CommunityKeywordDTO() {
    }

    public CommunityKeywordDTO(int keywordCode, String keywordName) {
        this.keywordCode = keywordCode;
        this.keywordName = keywordName;
    }

    public int getKeywordCode() {
        return keywordCode;
    }

    public void setKeywordCode(int keywordCode) {
        this.keywordCode = keywordCode;
    }

    public String getKeywordName() {
        return keywordName;
    }

    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName;
    }

    @Override
    public String toString() {
        return "CommunityKeywordDTO{" +
                "keywordNum=" + keywordCode +
                ", keywordName='" + keywordName + '\'' +
                '}';
    }
}
