package com.teddybear6.toegeungil.user.repository;

import com.teddybear6.toegeungil.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findByUserEmail(String email);
    UserEntity findById(int userNo);

}
