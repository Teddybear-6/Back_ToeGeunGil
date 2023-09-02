package com.teddybear6.toegeungil.keyword.dto;

import com.teddybear6.toegeungil.keyword.entity.Keyword;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class KeywordDTO {



    private int keywordCode;


    private String keywordName;

    public KeywordDTO(int keywordCode, String keywordName) {
        this.keywordCode = keywordCode;
        this.keywordName = keywordName;
    }

    public KeywordDTO(Keyword keyword) {
        this.keywordCode = keyword.getKeywordCode();
        this.keywordName = keyword.getKeywordName();
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
        return "KeywordDTO{" +
                "keywordCode=" + keywordCode +
                ", keywordName='" + keywordName + '\'' +
                '}';
    }
}
