package com.teddybear6.toegeungil.hobby.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HobbyPk implements Serializable {

    @Column(name = "hobby_code")
    private int hobbyCode;



    @Column(name = "keyword_code")
    private int keywordCode;



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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HobbyPk hobbyPk = (HobbyPk) o;
        return hobbyCode == hobbyPk.hobbyCode && keywordCode == hobbyPk.keywordCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hobbyCode, keywordCode);
    }
}
