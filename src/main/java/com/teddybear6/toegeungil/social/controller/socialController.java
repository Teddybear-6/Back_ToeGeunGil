package com.teddybear6.toegeungil.social.controller;

import com.teddybear6.toegeungil.social.entity.File;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/socials") //도메인
public class socialController {

    /*
    소셜(social) RestAPI
    - <GET> /social: 모임(커뮤니티) 조회
    - <GET> /social/{socialID} : 모임(커뮤니티) 상세 조회
    - <POST> /social: 모임(커뮤니티) 생성
    - <PUT> /social/{socialID} : 모임(커뮤니티) 수정
    - <DELETE> /social/{socialID} : 모임(커뮤니티) 삭제 */

    @GetMapping
    public ResponseEntity<String> test() {
        System.out.println("안녕 난 테스트");

        return ResponseEntity.status(200).body("안녕 나 포스트맨에서 보여?");
    }



}
