package com.teddybear6.toegeungil.hobby.dto;

import com.teddybear6.toegeungil.keyword.entity.Keyword;

public class HobbyKeywordDTO {

    private int keywordCode;
    private String keywordName;

    public HobbyKeywordDTO() {
    }

    public HobbyKeywordDTO(int keywordCode, String keywordName) {
        this.keywordCode = keywordCode;
        this.keywordName = keywordName;
    }

    public HobbyKeywordDTO(Keyword keyword) {
        this.keywordCode = keyword.getKeywordCode();
        this.keywordName =keyword.getKeywordName();
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
        return "keywordDTO{" +
                "keywordCode=" + keywordCode +
                '}';
    }
}
