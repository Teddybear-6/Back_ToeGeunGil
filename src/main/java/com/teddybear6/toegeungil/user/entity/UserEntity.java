package com.teddybear6.toegeungil.user.entity;

import com.teddybear6.toegeungil.auth.vo.UserRole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "userNo")
    private String userNo;
    @Column(name = "userEmail")
    private String userEmail;
    @Column(name = "userPassword")
    private String userPassword;
    @Column(name = "userName")
    private String userName;
    @Column(name = "role")
    private UserRole role;


    public List<String> getRoleList(){
        if(this.role.getValue().length()>0){
            return Arrays.asList(this.role.getValue().split(","));
        }
        return new ArrayList<>();
    }

    public UserEntity() {
    }

    public UserEntity(String userNo, String userEmail, String userPassword, String userName, UserRole role) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.role = role;
    }



    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
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
                ", role=" + role +
                '}';
    }
}
