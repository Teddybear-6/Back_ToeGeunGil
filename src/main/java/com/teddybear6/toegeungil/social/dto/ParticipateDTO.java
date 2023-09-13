package com.teddybear6.toegeungil.social.dto;

import com.teddybear6.toegeungil.social.entity.Participate;

import javax.persistence.Column;

public class ParticipateDTO {

    private int participateNum; //참여번호

    private int socialNum; //게시글 번호

    private int userNum; //회원 번호 (게시글 참여를 누른 회원)

    public ParticipateDTO() {
    }

    public ParticipateDTO(Participate participate) {
        //Participate Entity를 ParticipateDTO 한 번에 담아주기 위해 생성
        this.participateNum = participate.getParticipateNum();
        this.socialNum = participate.getSocialNum();
        this.userNum = participate.getUserNum();
    }

    public ParticipateDTO(int participateNum, int socialNum, int userNum) {
        this.participateNum = participateNum;
        this.socialNum = socialNum;
        this.userNum = userNum;
    }

    /*Builder*/
    public ParticipateDTO participateNum(int participateNum) {
        this.participateNum = participateNum;
        return this;
    }

    public ParticipateDTO socialNum(int socialNum) {
        this.socialNum = socialNum;
        return this;
    }

    public ParticipateDTO userNum(int userNum) {
        this.userNum = userNum;
        return this;
    }

    public ParticipateDTO builder() {
        return new ParticipateDTO(participateNum, socialNum, userNum);
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

    /*Getter*/
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
        return "ParticipateDTO{" +
                "participateNum=" + participateNum +
                ", socialNum=" + socialNum +
                ", userNum=" + userNum +
                '}';
    }
}
