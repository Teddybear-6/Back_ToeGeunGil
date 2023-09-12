package com.teddybear6.toegeungil.authjwt.user.query.application;

import com.teddybear6.toegeungil.authjwt.user.command.domain.model.UserEntity;
import com.teddybear6.toegeungil.authjwt.user.query.domain.sevice.UserViewService;
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
