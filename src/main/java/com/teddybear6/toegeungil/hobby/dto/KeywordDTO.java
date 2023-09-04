package com.teddybear6.toegeungil.hobby.dto;

public class KeywordDTO {

    private int keywordCode;

    public KeywordDTO() {
    }

    public KeywordDTO(int keywordCode) {
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
        return "keywordDTO{" +
                "keywordCode=" + keywordCode +
                '}';
    }
}
