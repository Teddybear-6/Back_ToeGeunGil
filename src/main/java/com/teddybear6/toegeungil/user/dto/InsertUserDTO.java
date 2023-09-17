package com.teddybear6.toegeungil.user.dto;

import com.teddybear6.toegeungil.auth.vo.UserRole;


public class InsertUserDTO {

    private int userNo;

    private String userEmail;

    private String userPassword;

    private String userName;

    private String  role;
    private String nickName;
    public InsertUserDTO() {
    }

    public InsertUserDTO(int userNo, String userEmail, String userPassword, String userName, String role, String nickName) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.role = role;
        this.nickName = nickName;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "InsertUserDTO{" +
                "userNo='" + userNo + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", role=" + role +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}