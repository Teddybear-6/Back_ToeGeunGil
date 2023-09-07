package com.teddybear6.toegeungil.hobby.entity;

import javax.persistence.*;

@Entity(name = "hobby_join")
@Table(name = "hobby_join")
public class HobbyJoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "join_num")
    private int joinNum;


    @Column(name = "hobby_code")
    private int hobbyCode;


    @Column(name = "user_no")
    private int userNo;

    public HobbyJoin() {
    }

    public HobbyJoin(int joinNum, int hobbyCode, int userNo) {
        this.joinNum = joinNum;
        this.hobbyCode = hobbyCode;
        this.userNo = userNo;
    }

    public int getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(int joinNum) {
        this.joinNum = joinNum;
    }

    public int getHobbyCode() {
        return hobbyCode;
    }

    public void setHobbyCode(int hobbyCode) {
        this.hobbyCode = hobbyCode;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    @Override
    public String toString() {
        return "HobbyJoin{" +
                "joinNum=" + joinNum +
                ", hobbyCode=" + hobbyCode +
                ", userNo=" + userNo +
                '}';
    }
}
