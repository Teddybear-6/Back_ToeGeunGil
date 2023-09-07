package com.teddybear6.toegeungil.user.controller;

import com.teddybear6.toegeungil.user.entity.UserEntity;
import com.teddybear6.toegeungil.user.sevice.UserViewService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserViewService userViewService;
    public UserController(UserViewService userViewService){this.userViewService=userViewService;}

    public UserEntity findUserEmail(String userId){
        UserEntity user = userViewService.findUserEmail(userId);

        return user;
    }

}
