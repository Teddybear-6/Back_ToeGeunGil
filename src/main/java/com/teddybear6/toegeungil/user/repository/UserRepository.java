package com.teddybear6.toegeungil.user.repository;


import com.teddybear6.toegeungil.auth.vo.UserRole;
import com.teddybear6.toegeungil.user.entity.UserEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Repository
public class UserRepository {
    private  PasswordEncoder passwordEncoder;

    private UserEntity userEntity;
    private final UserEntityRepository userEntityRepository;

    public UserRepository(@Lazy PasswordEncoder passwordEncoder, UserEntityRepository userEntityRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userEntityRepository = userEntityRepository;
    }

    public UserEntity findUserEmail(String email){
        /*엔티티 레파지토리 호출해서 아이디 조회해주기*/
        UserEntity userEntity = userEntityRepository.findByUserEmail(email);
        return userEntity;
    }




}
