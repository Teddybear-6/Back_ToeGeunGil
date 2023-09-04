package com.teddybear6.toegeungil.hobby.entity;

import com.teddybear6.toegeungil.keyword.entity.Keyword;

import javax.persistence.*;

@Entity(name = "hobby_category")
@Table(name="hobby_category_mapping")
public class HobbyKeyword {

    @EmbeddedId
    private  HobbyPk hobbyPk;

    @MapsId("hobbyCode")
    @ManyToOne
    @JoinColumn(name = "hobby_code")
    private Hobby hobby;


    @MapsId("keywordCode")
    @ManyToOne
    @JoinColumn(name = "keyword_code")
    private Keyword keyword;

    public HobbyKeyword() {
    }


    public HobbyKeyword(HobbyPk hobbyPk, Hobby hobby, Keyword keyword) {
        this.hobbyPk = hobbyPk;
        this.hobby = hobby;
        this.keyword = keyword;
    }

    public HobbyPk getHobbyPk() {
        return hobbyPk;
    }

    public void setHobbyPk(HobbyPk hobbyPk) {
        this.hobbyPk = hobbyPk;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "HobbyKeyword{" +
                ", keyword=" + keyword +
                '}';
    }
}
