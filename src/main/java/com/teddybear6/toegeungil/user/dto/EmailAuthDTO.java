package com.teddybear6.toegeungil.user.dto;

public class EmailAuthDTO {
    public String email;

    public EmailAuthDTO() {
    }

    public EmailAuthDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailAuthDTO{" +
                "email='" + email + '\'' +
                '}';
    }

}
