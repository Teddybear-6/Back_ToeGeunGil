package com.teddybear6.toegeungil.auth.controller;

import com.teddybear6.toegeungil.user.entity.UserEntity;
import com.teddybear6.toegeungil.user.controller.UserController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app")
public class AuthController {
    private UserController userController;

    public AuthController(UserController userController){this.userController = userController;}

    public UserEntity findUserEmail(String userId){
        UserEntity user = userController.findUserEmail(userId);

        return user;
    }

    @GetMapping("/login")
    public String login(){ return "test/login";}


    @GetMapping("/loginfail")
    public ModelAndView loginFail(@RequestParam String errorMessage, ModelAndView mv){
        mv.addObject("message", errorMessage);
        mv.setViewName("test/loginFail");
        return mv;
    }




}
