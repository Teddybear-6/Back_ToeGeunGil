package com.teddybear6.toegeungil.social.controller;

import com.teddybear6.toegeungil.social.dto.FileDTO;
import com.teddybear6.toegeungil.social.dto.SocialDTO;
import com.teddybear6.toegeungil.social.entity.Social;
import com.teddybear6.toegeungil.social.service.SocialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.PrePersist;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
할 일
- 연관관계 맺기
- 카테고리 코드 -> 카테고리 이름
- 지역 코드 -> 지역 이름
- 키워드 코드 -> 키워드 이름 및 리스트로 묶어오기?
- 사진 코드 -> 사진 업로드 및 다운로드 작업부터 시작해보기...@@ 

완료
- 소셜, 카테고리, 키워드, 지역, 파일 엔티티 설계
- 소셜 엔티티 활용하여 CRUD 만들어보기
- 오늘 사진 업로드 및 다운로드 완료하기 !! 목표

- 마지막에 create 모드에서 none으로 수정하기!
*/

@RestController
@RequestMapping("/socials") //도메인
public class socialController {
    
    /*
    소셜 Entity
    - 소셜(Social)
    - 카테고리(Category)
    - 키워드(Keyword)
    - 지역(Local)
    - 사진파일(File) */

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

    @PutMapping //04_소셜 수정(/social{socialNum})
    public ResponseEntity<?> updateSocialPostNum(SocialDTO socialDTO) {
        /*
        update 과정
        ex) 1.변경전[0,0,0] -> 2.변경후[0,0,1] -> 3.save(id) 메서드 호출 후 변경 전;후 값 비교
            -> 4.영속성컨텍스트 [0,0,1] 저장 -> 5.DB에 반영*/

        //update를 위해 수정하고자 하는 값이 영속(존재) 상태인지 확인한다.
        Social findSocial = socialService.readSocialPostNum(socialDTO.getSocialNum());
        //영속성 컨텍스트에 존재하지 않을 경우, "해당 게시글이 존재하지 않습니다."
        if (Objects.isNull(findSocial)) {
            return ResponseEntity.status(404).body("해당 게시글이 존재하지 않습니다.");
        }

        SocialDTO social = socialDTO;
        int result = socialService.updateSocialPostNum(findSocial, social);
        System.out.println("result : " + result);
        if (result == 0) {
            return ResponseEntity.status(404).body("게시글 수정에 실패하였습니다...");
        } else {
            return ResponseEntity.ok().body("게시글이 수정되었습니다.");
        }
    }

    /*
    게시글 삭제는 수정에서 게시글 상태 Y -> N으로 변경*/

    @PostMapping("/image")
    public ResponseEntity<?> uploadSocialImage(@RequestParam(name = "image")MultipartFile image) throws IOException {
        String uploadImage = socialService.uploadSocialImage(image);
        return ResponseEntity.ok().body(uploadImage);
//        //폴더 생성과 파일명을 새로 부여하기 위한 현재 시간 가져오기
//        LocalDateTime now = LocalDateTime.now();
//        int year = now.getYear();
//        int month = now.getMonthValue();
//        int day = now.getDayOfMonth();
//        int hour = now.getHour();
//        int minute = now.getMinute();
//        int second = now.getSecond();
//        int millis = now.get(ChronoField.MILLI_OF_SECOND);
//
//        //파일이 저장될 절대 경로
//        String absolutePath = new File("/DEV/21_project02").getAbsolutePath() + "/";
//        System.out.println(absolutePath);
//        //새로 부여한 이미지명
//        String newFileName = "image" + hour + minute + second + millis;
//        System.out.println(newFileName);
//        //정규식을 이용한 확장자 추출
//        String fileExtension = '.' + image.getOriginalFilename().replaceAll("^.*\\.(.*)$", "$1");
//        System.out.println(fileExtension);
//        //저장될 폴더 경로
//        String path = "images/test/" + year + "/" + month + "/" + day;
//        System.out.println(path);
    }



}
