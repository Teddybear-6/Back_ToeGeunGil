package com.teddybear6.toegeungil.user.sevice;

import com.teddybear6.toegeungil.auth.vo.UserRole;
import com.teddybear6.toegeungil.user.dto.InsertUserDTO;
import com.teddybear6.toegeungil.user.entity.UserEntity;
import com.teddybear6.toegeungil.user.repository.UserEntityRepository;
import com.teddybear6.toegeungil.user.repository.UserRepository;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class UserViewService {
    private final UserRepository userRepository;
    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserViewService(UserRepository userRepository, UserEntityRepository userEntityRepository,@Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public UserEntity findUserEmail(String userId) {
        System.out.println("findUser");
        UserEntity userEntity = userRepository.findUserEmail(userId);
        return userEntity;
    }


    public int registUser(InsertUserDTO insertUserDTO) {

        UserEntity user = new UserEntity();
        user.setUserEmail(insertUserDTO.getUserEmail());
        user.setUserName(insertUserDTO.getUserName());
        user.setRole(UserRole.valueOf(insertUserDTO.getRole()));
        user.setUserPassword(passwordEncoder.encode(insertUserDTO.getUserPassword()));
        user.setNickName(insertUserDTO.getNickName());
        UserEntity findUser = userEntityRepository.save(user);
        if (Objects.isNull(findUser)) {
            return 0;
        } else {
            return 1;
        }

    }

    public UserEntity findById(int userNo) {
        UserEntity user = userEntityRepository.findById(userNo);

        return user;

    }


//    @Transactional
//    public int registName(UserEntity){
//        UserEntity user = userRepository.save(userRepository);
//        System.out.println(result);
//
//        if (Object.isNull(result)){
//            return 0;
//        }else {
//            return 1;
//        }
//    }
//    @Transactional
//    public void deleteCode(int del){
//        userRepository.deleteById(del);
//        FindUserEmail findUserEmail=userRepository.findById(del);
//        System.out.println(user);
//    }
}