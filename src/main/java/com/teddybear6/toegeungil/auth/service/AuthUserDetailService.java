package com.teddybear6.toegeungil.auth.service;

import com.teddybear6.toegeungil.auth.controller.AuthController;
import com.teddybear6.toegeungil.auth.dto.AuthUserDetail;
import com.teddybear6.toegeungil.user.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthUserDetailService implements UserDetailsService {
    private final AuthController authController;

    public AuthUserDetailService(AuthController authController) {this.authController = authController;}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity findUser =authController.findUserEmail(username);

        return new AuthUserDetail(findUser);
    }
}
