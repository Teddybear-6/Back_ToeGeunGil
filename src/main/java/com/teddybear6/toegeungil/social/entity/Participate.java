package com.teddybear6.toegeungil.social.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "social_participate")
public class Participate { //참여하기

    @Id
    @Column(name = "participate_num")
    private int participateNum; //참여번호

    @Column(name = "social_num")
    private int socialNum; //게시글 번호

    @Column(name = "user_num")
    private int userNum; //회원 번호 (게시글 참여를 누른 회원)

    public Participate() {
    }

    public Participate(int participateNum, int socialNum, int userNum) {
        this.participateNum = participateNum;
        this.socialNum = socialNum;
        this.userNum = userNum;
    }

    public int getParticipateNum() {
        return participateNum;
    }

    public void setParticipateNum(int participateNum) {
        this.participateNum = participateNum;
    }

    public int getSocialNum() {
        return socialNum;
    }

    public void setSocialNum(int socialNum) {
        this.socialNum = socialNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    @Override
    public String toString() {
        return "Participate{" +
                "participateNum=" + participateNum +
                ", socialNum=" + socialNum +
                ", userNum=" + userNum +
                '}';
    }
}
