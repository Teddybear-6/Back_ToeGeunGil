package com.teddybear6.toegeungil.authjwt.user.query.domain.repository;

import com.teddybear6.toegeungil.authjwt.auth.command.domain.aggregate.vo.UserRole;
import com.teddybear6.toegeungil.authjwt.user.command.domain.model.UserEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private PasswordEncoder passwordEncoder;

    public UserRepository(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity findUserEmail(String email){

        UserEntity findUser = null;
        if(email.equals("user@gmail.com")){
            findUser =  new UserEntity("1", "user@gmail.com", passwordEncoder.encode("pass"), "퇴근길", UserRole.USER);
        }
        return findUser;
    }
}
