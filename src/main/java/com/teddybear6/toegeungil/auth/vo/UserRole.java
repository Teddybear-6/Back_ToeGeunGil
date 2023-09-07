package com.teddybear6.toegeungil.auth.vo;

public enum UserRole {
    ADMIN("ADMIN"), USER("USER");

    private String value;

    UserRole(String value) {this.value =value;}

    public String getkey() {return name();}
    public String getValue(){return value;}
}
