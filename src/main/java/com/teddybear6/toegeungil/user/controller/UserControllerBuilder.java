package com.teddybear6.toegeungil.user.controller;

import com.teddybear6.toegeungil.user.sevice.UserViewService;

public class UserControllerBuilder {
    private UserViewService userViewService;

    public UserControllerBuilder setUserViewService(UserViewService userViewService) {
        this.userViewService = userViewService;
        return this;
    }

    public UserController createUserController() {
        return new UserController(userViewService);
    }
}