package com.teddybear6.toegeungil.Social.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/social")
public class SocialController {

    @GetMapping
    public void test() {
        System.out.println("test!");
    }
}
