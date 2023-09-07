package com.teddybear6.toegeungil.user.repository;

import com.teddybear6.toegeungil.auth.vo.UserRole;
import com.teddybear6.toegeungil.user.entity.UserEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private  PasswordEncoder passwordEncoder;

    public UserRepository(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity findUserEmail(String email){

        UserEntity findUser = null;
        if(email.equals("user@gmail.com")){
            findUser =  new UserEntity("1", "user@gmail.com", passwordEncoder.encode("pass"), "퇴근길", UserRole.TUTOR);
        }
        System.out.println("레포지토리");
        return findUser;
    }
}
