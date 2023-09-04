package com.teddybear6.toegeungil.community.controller;

import com.teddybear6.toegeungil.community.dto.CommunityDTO;
import com.teddybear6.toegeungil.community.entity.Community;
import com.teddybear6.toegeungil.community.service.CommunityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/communitys")
public class CommunityController {

    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping("/{communityNum}")
    public ResponseEntity<Object> findByCommunityCode(@PathVariable int communityNum){
        Community community = communityService.findByCommunityCode(communityNum);

        if(Objects.isNull(community)){
            return ResponseEntity.status(404).body(new String("잘못된 코드 입력"));
        }

        CommunityDTO communityDTO = new CommunityDTO(community);

        return ResponseEntity.ok().body(communityDTO);
    }

    @PostMapping
    public  ResponseEntity<?> registCommunity(CommunityDTO communityDTO) {

        int result = 0;
        try {
            result = communityService.registCommunity(communityDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (result > 0) {
            return ResponseEntity.ok().body("커뮤니티 등록에 성공하였습니다.");
        } else {
            return ResponseEntity.status(500).body("커뮤니티 등록에 실패하였습니다.");
        }
    }
}



