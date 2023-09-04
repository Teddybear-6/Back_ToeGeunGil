package com.teddybear6.toegeungil.hobby.entity;


import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class HobbyPk implements Serializable {



    @Column(name = "keyword_code")
    private int keywordCode;

    @Column(name = "hobby_code")
    private int hobbyCode;


    public HobbyPk() {
    }

    public HobbyPk(int keywordCode, int hobbyCode) {
        this.keywordCode = keywordCode;
        this.hobbyCode = hobbyCode;
    }

    public int getKeywordCode() {
        return keywordCode;
    }

    public void setKeywordCode(int keywordCode) {
        this.keywordCode = keywordCode;
    }

    public int getHobbyCode() {
        return hobbyCode;
    }

    public void setHobbyCode(int hobbyCode) {
        this.hobbyCode = hobbyCode;
    }

    @Override
    public String toString() {
        return "HobbyPk{" +
                "keywordCode=" + keywordCode +
                ", hobbyCode=" + hobbyCode +
                '}';
    }
}
