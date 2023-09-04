package com.teddybear6.toegeungil.social.controller;

import com.teddybear6.toegeungil.social.dto.SocialDTO;
import com.teddybear6.toegeungil.social.entity.Social;
import com.teddybear6.toegeungil.social.service.SocialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PrePersist;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/socials") //도메인
public class socialController {

    private final SocialService socialService;

    public socialController(SocialService socialService) {
        this.socialService = socialService;
    }

    /*
    소셜(social) RestAPI
    - <GET> /social: 소셜 전체 조회
    - <GET> /social/{socialID} : 소셜 상세 조회
    - <POST> /social: 소셜 등록
    - <PUT> /social/{socialID} : 소셜 수정
    - <DELETE> /social/{socialID} : 소셜 삭제 */

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        System.out.println("안녕 난 테스트");

        return ResponseEntity.status(200).body("안녕 나 포스트맨에서 보여?");
    }

    @GetMapping //01_소셜 전체 조회(/social)
    public ResponseEntity<List<?>> readAllSocial() {
        List<Social> socialList = socialService.readAllSocial();

        if (socialList.size() <= 0) {
            return ResponseEntity.status(404).body((Collections.singletonList("error")));
        } else {
            //객체의 getter로 List를 만든다.
            List<SocialDTO> socialDTOList = socialList.stream().map(social -> new SocialDTO(social)).collect(Collectors.toList());
            return ResponseEntity.ok().body(socialDTOList);
        }
    }

    @GetMapping("{socialNum}") //02_소셜 부분 조회(/social/{socialNum})
    public ResponseEntity<?> readSocialPostNum(@PathVariable int socialNum) {
        Social social = socialService.readSocialPostNum(socialNum);

        if (Objects.isNull(social)) {
            return ResponseEntity.status(404).body("게시글 조회에 실패하였습니다...");
        } else {
            SocialDTO socialDTO = new SocialDTO(social);
            return ResponseEntity.ok().body(socialDTO);
        }
    }

    @PostMapping //03_소셜 등록(/social)
    public ResponseEntity<?> SocialPostRegistration(SocialDTO socialDTO) {
        Social social = new Social(socialDTO);
        social.setPostRegDate(new Date()); //게시글 등록일

        social.setSocialDate(new Date()); //모임일
        social.setSocialStartTime(new Date()); //모임시작시간
        social.setSocialEndTime(new Date()); //모임종료시간


        int result = socialService.SocialPostRegistration(social);
        if (result == 0) {
            //socialService.SocialPostRegistration에서 반환받은 값이 0일 경우
            return ResponseEntity.status(404).body("게시글 등록에 실패하였습니다...");
        } else {
            return ResponseEntity.ok().body("게시글이 등록되었습니다");
        }
    }

}
