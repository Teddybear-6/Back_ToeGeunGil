package com.teddybear6.toegeungil.community.controller;

import com.teddybear6.toegeungil.community.entity.Community;
import com.teddybear6.toegeungil.community.service.CommunityService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/community") // 요청이 왔을 때 호출되는 주소
public class CommunityController {

    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }
    @GetMapping("/{communityCode}")
    public ResponseEntity<Object> communityFindCode(@PathVariable int communityCode){
        Community community = CommunityService.communityFindCode(communityCode);

        if(Objects.isNull(community)){
            return ResponseEntity.status(404).body(new String("잘못된 코드 입력"));
        }
        return ResponseEntity.ok().body(community);
    }

}


