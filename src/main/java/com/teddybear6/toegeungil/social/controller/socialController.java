package com.teddybear6.toegeungil.social.controller;

import com.teddybear6.toegeungil.category.entity.Category;
import com.teddybear6.toegeungil.social.dto.ParticipateDTO;
import com.teddybear6.toegeungil.social.dto.SocialDTO;
import com.teddybear6.toegeungil.social.entity.Participate;
import com.teddybear6.toegeungil.social.entity.Social;
import com.teddybear6.toegeungil.social.service.SocialService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
- 사진 업로드 및 다운로드 완료하기

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
    사진 https://velog.io/@mooh2jj/SpringBoot-File-uploaddownload-%EA%B5%AC%ED%98%84*/
    @PostMapping("/image") //10_사진 업로드
    public ResponseEntity<?> uploadSocialImage(@RequestParam(name = "image"/*key*/) MultipartFile image) throws IOException {
        String uploadImage = socialService.uploadSocialImage(image);
        return ResponseEntity.ok().body(uploadImage);
    }

    @GetMapping("/image/{imageName}") //11_사진 다운로드
    public ResponseEntity<?> downloadSocialImage(@PathVariable("imageName") String imageName) {
        byte[] downloadImage = socialService.downloadSocialImage(imageName);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(downloadImage);
    }


    /*
    참여하기*/
    @GetMapping("/participate/{socialNum}") //20_소셜 참여 회원 조회(/participate/{게시글 번호})
    public ResponseEntity<List<?>> readSocialParticipateUser(@PathVariable int socialNum) {
        //소셜
        List<Participate> participateList = socialService.readSocialParticipateUser(socialNum);
        System.out.println(participateList);

        return ResponseEntity.ok().body(participateList);
    }

    @PostMapping("/participate/{socialNum}") //21_소셜 참여(/participate)
    public ResponseEntity<?> SocialParticipateRegistration(@PathVariable int socialNum, ParticipateDTO participateDTO) {
        //참여하기(게시글번호 AND 회원번호)가 존재하는지 확인하기
        Participate findSocialParticipateRegistration = socialService.findSocialParticipateRegistration(participateDTO.getSocialNum(), participateDTO.getUserNum());
        if (!Objects.isNull(findSocialParticipateRegistration)) {
            //영속성 컨텍스트에 존재할 경우, "이미 참여 신청 되어있음"
            int result = socialService.SocialParticipateDelete(findSocialParticipateRegistration);
            return ResponseEntity.ok().body("모임 참여가 취소되었습니다.");
        } else {
            //참여가 등록되어있지 않을 경우, 참여 등록
            Participate participate = new Participate(participateDTO); //setter를 생성해주지 않으면 값이 안넘어옴...왜지?
            participate.socialNum(socialNum).builder();

            int result = socialService.SocialParticipateRegistration(participate);
            if (result == 0) {
                //socialService.SocialParticipateRegistration 반환받은 값이 0일 경우
                return ResponseEntity.status(404).body("참여에 실패하였습니다.");
            } else {
                return ResponseEntity.ok().body("모임에 참여되었습니다.");
            }
        }
    }


    /*
    필터*/
    @GetMapping("/category/{categoryCode}") //30_카테고리 코드 필터
    public ResponseEntity<List<?>> readSocialPostCategory(@PathVariable int categoryCode) {
        //카테고리 코드 받아오기
        Category category = socialService.readSocialPostCategory(categoryCode);
        //받아온 카테고리 코드로 해당 게시글 리스트로 받아오기
        List<Social> socialList = socialService.readSocialPostWhereCategoryCode(categoryCode);

        return ResponseEntity.ok().body(socialList);
    }

}
