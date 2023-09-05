package com.teddybear6.toegeungil.community.controller;

import com.teddybear6.toegeungil.community.dto.CommunityDTO;
import com.teddybear6.toegeungil.community.entity.Community;
import com.teddybear6.toegeungil.community.service.CommunityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/communitys")
public class CommunityController {

    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping
    public ResponseEntity<List<?>> findAllCommunity(){
        List<Community> communityList = communityService.findAllCommunity();
        if(communityList.size() == 0){
            List<String> error = new ArrayList<>();
            error.add("커뮤니티 글이 존재하지 않습니다.");
            return ResponseEntity.status(404).body(error);
        }

        return ResponseEntity.ok().body(communityList);
    }

    @GetMapping("/{communityNum}") // 커뮤니티 번호로 조회
    public ResponseEntity<Object> findByCommunityCode(@PathVariable int communityNum){
        Community community = communityService.findByCommunityCode(communityNum);

        if(Objects.isNull(community)){
            return ResponseEntity.status(404).body(new String("잘못된 코드 입력"));
        }

        CommunityDTO communityDTO = new CommunityDTO(community);

        return ResponseEntity.ok().body(communityDTO);
    }

    @PostMapping // 커뮤니티 등록하기
    public ResponseEntity<?> registCommunity(CommunityDTO communityDTO) {

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

//    @PutMapping("/{communityNum}") // 커뮤니티 수정
//    public ResponseEntity<?> updateCommunity(@PathVariable int communityNum, @RequestParam String communityTitle, @RequestParam String communityIntro,
//                                             @RequestParam int categoryNum, @RequestParam int keywordNum, @RequestParam int locationNum, @RequestParam String communityStatus){
//
//        // 유효성 검사 체크 (RequestParam)으로 코드만 받아오고 나머지는 service 로직에서 찾아오기 .,...
//
//        Community findCommunity = communityService.findByCommunityCode(communityNum);
//
//        if(Objects.isNull(findCommunity)){
//            return ResponseEntity.status(404).body("커뮤니티 카테고리가 존재하지 않습니다.");
//        }
//
//        // 직접 각 필드의 값을 전달하여 communityUpdate 메서드를 호출한다.
//        int result = communityService.communityUpdate(findCommunity, communityTitle, communityIntro, categoryNum, keywordNum, locationNum, communityStatus);
//
//        if(result > 0){
//            return ResponseEntity.ok().body("커뮤니티 수정에 성공했습니다.");
//        } else {
//            return ResponseEntity.status(400).body("커뮤니티 수정에 실패하였습니다.");
//        }
//    }

    @PutMapping("/{communityNum}") // 커뮤니티 수정
    public ResponseEntity<?> updateCommunity(@PathVariable int communityNum, @RequestBody CommunityDTO communityDTO){

        // 유효성 검사 체크 (RequestParam)으로 코드만 받아오고 나머지는 service 로직에서 찾아오기 .,...

        Community findCommunity = communityService.findByCommunityCode(communityNum);

        if(Objects.isNull(findCommunity)){
            return ResponseEntity.status(404).body("커뮤니티 카테고리가 존재하지 않습니다.");
        }

        // 직접 각 필드의 값을 전달하여 communityUpdate 메서드를 호출한다.
        int result = communityService.communityUpdate(findCommunity, communityDTO);

        if(result > 0){
            return ResponseEntity.ok().body("커뮤니티 수정에 성공했습니다.");
        } else {
            return ResponseEntity.status(400).body("커뮤니티 수정에 실패하였습니다.");
        }
    }

    @DeleteMapping("/{communityNum}")
    public ResponseEntity<?> deleteCommunity(@PathVariable int communityNum){

        Community community = communityService.findByCommunityCode(communityNum);

        if(Objects.isNull(community)){
            return ResponseEntity.status(404).body("커뮤니티가 존재하지 않습니다.");
        }

        int result = communityService.deleteCommunityId(communityNum);

        if(result > 0){
            return ResponseEntity.ok().body("커뮤니티가 삭제 완료되었습니다.");
        }else {
            return ResponseEntity.status(500).body("커뮤니티가 삭제되지 않았습니다.");
        }
    }


}



