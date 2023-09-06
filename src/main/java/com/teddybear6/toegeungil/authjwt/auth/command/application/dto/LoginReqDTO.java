package com.teddybear6.toegeungil.authjwt.auth.command.application.dto;

import com.teddybear6.toegeungil.authjwt.auth.command.domain.aggregate.vo.UserRole;

public class LoginReqDTO {
    private int userNo;
    private String userName;
    private UserRole userRole;
    private String email;


    public LoginReqDTO() {
    }

    public LoginReqDTO(int userNo, String userName, UserRole userRole, String email) {
        this.userNo = userNo;
        this.userName = userName;
        this.userRole = userRole;
        this.email = email;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "LoginReqDTO{" +
                "userNo=" + userNo +
                ", userName='" + userName + '\'' +
                ", userRole=" + userRole +
                ", email='" + email + '\'' +
                '}';
    }
}
