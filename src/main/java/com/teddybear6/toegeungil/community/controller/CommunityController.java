package com.teddybear6.toegeungil.community.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teddybear6.toegeungil.community.dto.CommunityDTO;
import com.teddybear6.toegeungil.community.dto.CommunityKeywordDTO;
import com.teddybear6.toegeungil.community.entity.Community;
import com.teddybear6.toegeungil.community.entity.CommunityKeyword;
import com.teddybear6.toegeungil.community.service.CommunityService;
import com.teddybear6.toegeungil.keyword.entity.Keyword;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/communitys")
@CrossOrigin(origins = "http://localhost:3000")
public class CommunityController {

    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping // 커뮤니티 전체 조회
    public ResponseEntity<List<?>> findAllCommunity() {
        List<CommunityDTO> communityList = communityService.findAllCommunity();

        if (communityList.isEmpty()) {
            return ResponseEntity.status(404).body(Collections.singletonList("error"));
        } else {
            return ResponseEntity.ok().body(communityList);
        }
    }


    @GetMapping("/{communityNum}") // 커뮤니티 번호로 부분 조회
    public ResponseEntity<Object> findByCommunityCode(@PathVariable int communityNum){
        Community community = communityService.findByCommunityCode(communityNum);

        if(Objects.isNull(community)) {
            return ResponseEntity.status(404).body(new String("잘못된 코드 입력"));
        }else {
            CommunityDTO communityDTO = new CommunityDTO(community);
            List<Keyword> keywordList = new ArrayList<>();
            for (int i = 0; i < community.getCommunityKeywordList().size(); i++) {
                keywordList.add(community.getCommunityKeywordList().get(i).getKeyword());
            }
            List<CommunityKeywordDTO> communityKeywordDTOList = keywordList.stream().map(m -> new CommunityKeywordDTO(m)).collect(Collectors.toList());
            communityDTO.setCommunityKeywordDTOList(communityKeywordDTOList);

            return ResponseEntity.ok().body(communityDTO);
        }
    }

    @PostMapping // 커뮤니티 등록하기
    public ResponseEntity<?> registCommunity(@RequestPart("community") CommunityDTO communityDTO) {

        communityDTO.setPostWriteDate(new Date());
        System.out.println(communityDTO);
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

    @GetMapping("/list")
    public ResponseEntity<List<CommunityDTO>> CommunityList(@RequestParam(name = "categoryNum", required = false) Integer categoryNum,
                                                            @RequestParam(name = "locationNum", required = false) Integer locationNum){

        List<CommunityDTO> communityList = communityService.CommunityListFilters(categoryNum,locationNum);

        return ResponseEntity.ok(communityList);
    }


}
