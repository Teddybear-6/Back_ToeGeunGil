package com.teddybear6.toegeungil.keyword.entity;

import javax.persistence.*;

@Entity(name = "keyword")
@Table(name = "keyword")
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_code")
    private int keywordCode;

    @Column(name = "keyword_name")
    private String keywordName;


    public Keyword() {
    }

    public Keyword(int keywordCode, String keywordName) {
        this.keywordCode = keywordCode;
        this.keywordName = keywordName;
    }

    public Keyword(Integer keywordNum) {
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
        return "Keyword{" +
                "keywordCode=" + keywordCode +
                ", keywordName='" + keywordName + '\'' +
                '}';
    }
}