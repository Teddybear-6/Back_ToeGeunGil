package com.teddybear6.toegeungil.social.entity;

import com.teddybear6.toegeungil.social.dto.ParticipateDTO;

import javax.persistence.*;

@Entity
@Table(name = "social_participate")
public class Participate { //참여하기

    @Id
    @Column(name = "participate_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 관리하는 전략 사용
    private int participateNum; //참여번호

    @Column(name = "social_num")
    private int socialNum; //게시글 번호

    @Column(name = "user_num")
    private int userNum; //회원 번호 (게시글 참여를 누른 회원)

    public Participate() {
    }

    public Participate(ParticipateDTO participateDTO) {
        this.participateNum = participateDTO.getParticipateNum();
        this.socialNum = participateDTO.getSocialNum();
        this.userNum = participateDTO.getUserNum();
    }

    public Participate(int participateNum, int socialNum, int userNum) {
        this.participateNum = participateNum;
        this.socialNum = socialNum;
        this.userNum = userNum;
    }

    /*Builder*/
    public Participate participateNum(int participateNum) {
        this.participateNum = participateNum;
        return this;
    }

    public Participate socialNum(int socialNum) {
        this.socialNum = socialNum;
        return this;
    }

    public Participate userNum(int userNum) {
        this.userNum = userNum;
        return this;
    }

    public Participate builder() {
        return new Participate(participateNum, socialNum, userNum);
    }

    public void setParticipateNum(int participateNum) {
        this.participateNum = participateNum;
    }

    public void setSocialNum(int socialNum) {
        this.socialNum = socialNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    /*getter*/
    public int getParticipateNum() {
        return participateNum;
    }

    public int getSocialNum() {
        return socialNum;
    }

    public int getUserNum() {
        return userNum;
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
