package com.teddybear6.toegeungil.auth.service;

import com.teddybear6.toegeungil.auth.controller.AuthController;
import com.teddybear6.toegeungil.auth.dto.AuthUserDetail;
import com.teddybear6.toegeungil.user.entity.UserEntity;
import com.teddybear6.toegeungil.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;


    public AuthUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        System.out.println("여기도 되나");
        UserEntity findUser  =userRepository.findUserEmail(username);
        System.out.println(findUser+"desv");
        return new AuthUserDetail(findUser);
    }




}
