package com.teddybear6.toegeungil.user.sevice;

import com.teddybear6.toegeungil.auth.vo.UserRole;
import com.teddybear6.toegeungil.user.dto.FindPassDTO;
import com.teddybear6.toegeungil.user.dto.InsertUserDTO;
import com.teddybear6.toegeungil.user.entity.UserEntity;
import com.teddybear6.toegeungil.user.repository.UserEntityRepository;
import com.teddybear6.toegeungil.user.repository.UserRepository;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class UserViewService {
    private final UserRepository userRepository;
    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserViewService(UserRepository userRepository,  UserEntityRepository userEntityRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public UserEntity findUserEmail(String userId) {
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


    // 비밀번호 찾기
    public String finduserpass(FindPassDTO passDTO /*/*이름 닉네임 이메일 받기*/) {

        UserEntity user = userEntityRepository.findByUserEmail(passDTO.getEmail() /*이름 닉네임 이메일 넣기*/);

        System.out.println(user);
        if(user.getUserName()==passDTO.getName()){
            return passwordEncoder.encode(user.getUserPassword());
        }else {
            return null;
        }
        // 회원이 있으면  비밀번호를 복화해서 알려주고
        // 없으면 존재하지 않는 회원입니다.

    }

    public void setPassword(UserEntity user, String authCode) {
        user.setUserPassword(passwordEncoder.encode(authCode));
        userEntityRepository.save(user);
    }


    // 임시비밀번호 바꿔주는 함수
//    @Transactional
//    public boolean updateTempPwd(String email, String randnum8){
//        try {
//            UserEntity user = userRepository.findUserEmail(email);
//            BCryptPasswordEncoder bCryptPasswordEncoder = userRepository.findUserEmail(String email);
//            return yes;
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return no;
//    }
//
//
//
//
//    public boolean sendTemPwd(String email, int num) {
//        try{
//            String passwordEncoder= userRepository.GetRandomNum(8);
//            if (userRepository.findUserEmail(email, temppwd)){
//                return sendemail.sendEmail(email, "퇴근길 관리자입니다", "임시번호는"+ 'tmpped' +"입니다");
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//
//    }



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