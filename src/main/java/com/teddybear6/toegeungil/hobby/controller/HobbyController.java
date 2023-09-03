package com.teddybear6.toegeungil.hobby.controller;

import com.teddybear6.toegeungil.hobby.dto.HobbyDTO;
import com.teddybear6.toegeungil.hobby.service.HobbyService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.zip.Deflater;

@RestController
@RequestMapping("/hobby")
public class HobbyController {

    /*  해야할일
    *   최대인원
    *   강사이름
    *   일정(날짜 시간 장소)
    *   카테고리
    *   키워드
    *   가격
    *   마감일
    *   참여자
    *   신청전 확인해주세요
    *   강사 소개
    *   사진 4장
    *   마감여부
    *
    *   마감되었을때 참가자 한하여 후기글 + 점수
    *   선생이 다는 댓글
    *
    * 1.리드 부터 해보자
     * 전체 조회는 사진 제목 가격 카테고리 키워드만 보이면 된다
     */

    private  final HobbyService hobbyService;

    public HobbyController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }
    //    @GetMapping
//    public ResponseEntity<List<?>> findAllHobby(){
//
//    }

    //이미지테스트 파일을 db에 저장
    @PostMapping("/images")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException{
        String uploadImage = hobbyService.uploadImage(file);
        return ResponseEntity.ok().body(uploadImage);
    }

    //이미지 다운로드
    @GetMapping("/images/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable("fileName") String fileName){
        System.out.println(fileName);

        byte [] downloadImage = hobbyService.downloadImage(fileName);
        return ResponseEntity.ok().contentType(MediaType.valueOf("image/png"))
                .body(downloadImage);
    }



    @PostMapping
    public ResponseEntity<?> registHobby()

}
