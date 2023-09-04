package com.teddybear6.toegeungil.community.controller;

import com.teddybear6.toegeungil.community.dto.CommunityDTO;
import com.teddybear6.toegeungil.community.entity.Community;
import com.teddybear6.toegeungil.community.service.CommunityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/communitys")
public class CommunityController {

    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping("/{communityCode}")
    public ResponseEntity<Object> findByCommunityCode(@PathVariable int communityCode){
        Community community = communityService.findByCommunityCode(communityCode);

        if(Objects.isNull(community)){
            return ResponseEntity.status(404).body(new String("잘못된 코드 입력"));
        }

        CommunityDTO communityDTO = new CommunityDTO(community);

        return ResponseEntity.ok().body(communityDTO);
    }
}



