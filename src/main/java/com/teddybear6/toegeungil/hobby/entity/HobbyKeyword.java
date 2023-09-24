package com.teddybear6.toegeungil.hobby.entity;

import com.teddybear6.toegeungil.keyword.entity.Keyword;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "hobby_category")
@Table(name="hobby_keyword_mapping")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HobbyKeyword keyword1 = (HobbyKeyword) o;
        return Objects.equals(hobbyPk, keyword1.hobbyPk) && Objects.equals(hobby, keyword1.hobby) && Objects.equals(keyword, keyword1.keyword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hobbyPk, hobby, keyword);
    }
}
