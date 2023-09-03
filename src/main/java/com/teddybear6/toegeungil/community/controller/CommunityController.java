package com.teddybear6.toegeungil.community.controller;

import com.teddybear6.toegeungil.community.dto.CommunityDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/community") // 요청이 왔을 때 호출되는 주소
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping("/community")
    public ModelAndView community(ModelAndView mv, HttpServletRequest request, HttpServletResponse response){


    }


}


