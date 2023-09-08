package com.teddybear6.toegeungil.hobby.dto;

import com.teddybear6.toegeungil.keyword.entity.Keyword;

public class HobbyKeywordDTO {

    private int keywordCode;

    public HobbyKeywordDTO() {
    }

    public HobbyKeywordDTO(int keywordCode) {
        this.keywordCode = keywordCode;
    }
    public HobbyKeywordDTO(Keyword keyword) {
        this.keywordCode = keyword.getKeywordCode();
    }

    public int getKeywordCode() {
        return keywordCode;
    }

    public void setKeywordCode(int keywordCode) {
        this.keywordCode = keywordCode;
    }

    @Override
    public String toString() {
        return "keywordDTO{" +
                "keywordCode=" + keywordCode +
                '}';
    }
}
