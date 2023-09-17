package com.teddybear6.toegeungil.user.entity;


import com.teddybear6.toegeungil.auth.vo.UserRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "user_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNo;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "user_name")
    private String userName;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "role")
    private UserRole role;


    public List<String> getRoleList() {
        if (this.role.getValue().length() > 0) {
            return Arrays.asList(this.role.getValue().split(","));
        }
        return new ArrayList<>();
    }


    public UserEntity() {
    }


    public UserEntity(int userNo, String userEmail, String userPassword, String userName, String nickName, UserRole role) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.nickName = nickName;
        this.role = role;
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

    public UserRole getRole() {
        return role;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "userNo='" + userNo + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", role=" + role +
                '}';
    }
}