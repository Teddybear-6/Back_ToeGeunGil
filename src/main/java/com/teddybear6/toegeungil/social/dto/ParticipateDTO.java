package com.teddybear6.toegeungil.social.dto;

import javax.persistence.Column;

public class ParticipateDTO {

    private int participateNum; //참여번호

    private int socialNum; //게시글 번호

    private int userNum; //회원 번호 (게시글 참여를 누른 회원)

    public ParticipateDTO() {
    }

    public ParticipateDTO(int participateNum, int socialNum, int userNum) {
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
        return "ParticipateDTO{" +
                "participateNum=" + participateNum +
                ", socialNum=" + socialNum +
                ", userNum=" + userNum +
                '}';
    }
}
