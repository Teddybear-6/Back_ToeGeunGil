package com.teddybear6.toegeungil.social.controller;

import com.teddybear6.toegeungil.auth.dto.AuthUserDetail;
import com.teddybear6.toegeungil.category.entity.Category;
import com.teddybear6.toegeungil.keyword.entity.Keyword;
import com.teddybear6.toegeungil.local.entity.Local;
import com.teddybear6.toegeungil.social.dto.ParticipateDTO;
import com.teddybear6.toegeungil.social.dto.SocialDTO;
import com.teddybear6.toegeungil.social.dto.SocialImageDTO;
import com.teddybear6.toegeungil.social.dto.SocialKeywordDTO;
import com.teddybear6.toegeungil.social.entity.Participate;
import com.teddybear6.toegeungil.social.entity.Social;
import com.teddybear6.toegeungil.social.entity.SocialImage;
import com.teddybear6.toegeungil.social.service.SocialService;
import com.teddybear6.toegeungil.user.entity.UserEntity;
import com.teddybear6.toegeungil.user.sevice.UserViewService;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
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
@CrossOrigin(origins = "http://localhost:3000")
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
    private final UserViewService userViewService;

    public socialController(SocialService socialService, UserViewService userViewService) {
        this.socialService = socialService;
        this.userViewService = userViewService;
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
    public ResponseEntity<List<?>> readAllSocial(final Pageable pageable) {
        List<SocialDTO> socialList = socialService.readAllSocial(pageable);

        if (socialList.size() <= 0) {
            return ResponseEntity.status(404).body((Collections.singletonList("error")));
        } else {
            //객체의 getter로 List를 만든다.
//            List<SocialDTO> socialDTOList = socialList.stream().map(social -> new SocialDTO(social)).collect(Collectors.toList());
            return ResponseEntity.ok().body(socialList);
        }
    }

    @GetMapping("{socialNum}") //02_소셜 부분 조회(/social/{socialNum})
    public ResponseEntity<?> readSocialPostNum(@PathVariable int socialNum) {
        Social social = socialService.readSocialPostNum(socialNum);

        if (Objects.isNull(social)) {
            return ResponseEntity.status(404).body("게시글 조회에 실패하였습니다...");
        } else {
            SocialDTO socialDTO = new SocialDTO(social);
            List<Keyword> keywordList = new ArrayList<>();
            for (int i = 0; i < social.getSocialKeywordList().size(); i++) {
                keywordList.add(social.getSocialKeywordList().get(i).getKeyword());
            }
            List<SocialKeywordDTO> socialKeywordDTOList = keywordList.stream().map(m -> new SocialKeywordDTO(m)).collect(Collectors.toList());
            socialDTO.setKeywordDTOList(socialKeywordDTOList);

            return ResponseEntity.ok().body(socialDTO);
        }
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','TUTOR')")
    @PostMapping //03_소셜 등록(/social)
    public ResponseEntity<?> SocialPostRegistration(@RequestPart("social") SocialDTO socialDTO, @RequestPart("image") MultipartFile file, @AuthenticationPrincipal AuthUserDetail userDetails) { //@RequestBody -> Json으로 넘기기위해 필요한 친구

        UserEntity userEntity = userViewService.findUserEmail(userDetails.getUserEntity().getUserEmail());
        Map<String, String> respose = new HashMap<>();
        if (Objects.isNull(userEntity)) {
            respose.put("value", "회원이 아닙니다.");
            return ResponseEntity.status(500).body(respose);
        }

        int result = 0;

        try {
            socialDTO.setPostRegDate(new Date()); //게시글 등록일
            socialDTO.setUserNum(userEntity.getUserNo());
            result = socialService.SocialPostRegistration(socialDTO, file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if (result == 0) {
            //socialService.SocialPostRegistration에서 반환받은 값이 0일 경우
            return ResponseEntity.status(404).body("게시글 등록에 실패하였습니다...");
        } else {
            return ResponseEntity.ok().body("게시글이 등록되었습니다");
        }
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','TUTOR')")
    @PutMapping //04_소셜 수정(/social{socialNum})
    public ResponseEntity<?> updateSocialPostNum(@RequestPart("social") SocialDTO socialDTO, @RequestPart(value = "image", required = false) MultipartFile file, SocialImage socialImage) {
        /*
        update 과정
        ex) 1.변경전[0,0,0] -> 2.변경후[0,0,1] -> 3.save(id) 메서드 호출 후 변경 전;후 값 비교
            -> 4.영속성컨텍스트 [0,0,1] 저장 -> 5.DB에 반영*/

        //update를 위해 수정하고자 하는 값이 영속(존재) 상태인지 확인한다.
        System.out.println(socialDTO);

        Social findSocial = socialService.readSocialPostNum(socialDTO.getSocialNum());
        //영속성 컨텍스트에 존재하지 않을 경우, "해당 게시글이 존재하지 않습니다."
        if (Objects.isNull(findSocial)) {
            return ResponseEntity.status(404).body("해당 게시글이 존재하지 않습니다.");
        }

        SocialDTO social = socialDTO;
        social.setPostModiDate(new Date());
        int result = socialService.updateSocialPostNum(findSocial, social, file, socialImage);
        System.out.println("result : " + result);
        if (result == 0) {
            return ResponseEntity.status(404).body("게시글 수정에 실패하였습니다...");
        } else {
            return ResponseEntity.ok().body("게시글이 수정되었습니다.");
        }
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','TUTOR')")
    @DeleteMapping("/{socialNum}")
    public ResponseEntity<?> deleteScoailPostNum(@PathVariable int socialNum) {

        Social findSocial = socialService.readSocialPostNum(socialNum);
        //영속성 컨텍스트에 존재하지 않을 경우, "해당 게시글이 존재하지 않습니다."
        if (Objects.isNull(findSocial)) {
            return ResponseEntity.status(404).body("해당 게시글이 존재하지 않습니다.");
        }

        int result = socialService.deleteScoailPostNum(findSocial);
        if (result > 0) {
            return ResponseEntity.ok().body("게시글이 삭제되었습니다.");
        } else {
            return ResponseEntity.status(500).body("게시글 삭제에 실패하였습니다...");
        }

    }

    /*
    이미지 (수정) 2023.09.18*/
//    @PostMapping("/img") //이미지 업로드 (소셜 게시글 등록과 합침)
//    public ResponseEntity<?> uploadImage(@RequestPart("img") MultipartFile file, SocialImageDTO socialImageDTO) {
//        int result = 0;
//        try {
//            result = socialService.uploadImage(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//
//        if (result == 0) {
//            //socialService.SocialPostRegistration에서 반환받은 값이 0일 경우
//            return ResponseEntity.status(404).body("이미지 등록에 실패하였습니다...");
//        } else {
//            return ResponseEntity.ok().body("이미지가 등록되었습니다");
//        }
//    }

    @GetMapping("/img/{socialNum}") //사진 다운로드(최종)
    public ResponseEntity<?> downloadImage(@PathVariable int socialNum) {
        SocialImage socialImage = socialService.downloadImage(socialNum);

        if (Objects.isNull(socialImage)) {
            return ResponseEntity.status(404).body("이미지 조회에 실패하였습니다...");
        } else {
            SocialImageDTO socialImageDTO = new SocialImageDTO(socialImage);
            return ResponseEntity.ok().body(socialImageDTO);
//            "http://106.250.199.126:9000/image/"+socialImageDTO.getPath()
        }
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

    @PostMapping("/participate/{socialNum}/{userNum}") //21_소셜 참여(/participate)
    public ResponseEntity<?> SocialParticipateRegistration(@PathVariable int socialNum, ParticipateDTO participateDTO, @PathVariable int userNum) {
        //참여하기(게시글번호 AND 회원번호)가 존재하는지 확인하기
        Participate findSocialParticipateRegistration = socialService.findSocialParticipateRegistration(participateDTO.getSocialNum(), participateDTO.getUserNum());
        if (!Objects.isNull(findSocialParticipateRegistration)) {
            //영속성 컨텍스트에 존재할 경우, "이미 참여 신청 되어있음"
            int result = socialService.SocialParticipateDelete(findSocialParticipateRegistration);
            return ResponseEntity.ok().body("모임 참여가 취소되었습니다.");
        } else {
            //참여가 등록되어있지 않을 경우, 참여 등록
            Participate participate = new Participate(participateDTO); //setter를 생성해주지 않으면 값이 안넘어옴...왜지?
            participate.socialNum(socialNum).userNum(userNum).builder();

            int result = socialService.SocialParticipateRegistration(participate);
            if (result == 0) {
                //socialService.SocialParticipateRegistration 반환받은 값이 0일 경우
                return ResponseEntity.status(404).body("참여에 실패하였습니다.");
            } else {
                return ResponseEntity.ok().body("모임에 참여되었습니다.");
            }
        }
    }

    @GetMapping("/participate/{socialNum}/{userNum}")
    public ResponseEntity<?> SocialParticipateRead(@PathVariable int socialNum, ParticipateDTO participateDTO, @PathVariable int userNum) {
        Participate findSocialParticipateRegistration = socialService.findSocialParticipateRegistration(participateDTO.getSocialNum(), participateDTO.getUserNum());

        if (Objects.isNull(findSocialParticipateRegistration)) {
            return ResponseEntity.ok().body(false);
        } else {
            return ResponseEntity.ok().body(true);
        }
    }


    /*
    필터*/
    @GetMapping("/category/{categoryCode}") //30_카테고리 코드 필터
    public ResponseEntity<List<?>> readSocialPostCategory(@PathVariable int categoryCode, final Pageable pageable) {
        //카테고리 코드 받아오기
        Category category = socialService.readSocialPostCategory(categoryCode);
        //받아온 카테고리 코드로 해당 게시글 리스트로 받아오기
        List<SocialDTO> socialDTOList = socialService.readSocialPostWhereCategoryCode(categoryCode, pageable);

        return ResponseEntity.ok().body(socialDTOList);
    }

    @GetMapping("/category/{categoryCode}/size") //30_1_카테고리 사이즈 필터
    public ResponseEntity<?> readSocialPostCategorySize(@PathVariable int categoryCode, final Pageable pageable) {
        //카테고리 코드 받아오기
        Category category = socialService.readSocialPostCategory(categoryCode);
        //받아온 카테고리 코드로 해당 게시글 리스트로 받아오기
        List<SocialDTO> socialList = socialService.readSocialPostWhereCategoryCode(categoryCode, pageable);

        return ResponseEntity.ok().body(socialList.size());
    }

    @GetMapping("local/{localCode}") //31_지역 코드 필터
    public ResponseEntity<List<?>> readSocialPostLocal(@PathVariable int localCode) {
        //지역 코드
        Local local = socialService.readSocialPostLocal(localCode);
        //받아온 지역 코드로 해당 게시글 리스트로 받아오기
        List<Social> socialList = socialService.readSocialPostWhereLocalCode(localCode);

        return ResponseEntity.ok().body(socialList);
    }

    @GetMapping("/category/{categoryCode}/local/{localCode}") //32_지역 AND 카테고리 필터
    public ResponseEntity<List<?>> readSocialFilterCategoryAndLocal(@PathVariable int categoryCode, @PathVariable int localCode, final Pageable pageable) {
        //카테고리 코드 받아오기
        Category category = socialService.readSocialPostCategory(categoryCode);
        System.out.println("Category : " + category);
        //지역 코드
        Local local = socialService.readSocialPostLocal(localCode);
        System.out.println("Local : " + local);

        //카테고리 AND 지역
        List<Social> social = socialService.readSocialFilterCategoryAndLocal(category, local);
        System.out.println("controller : " + social);

        return ResponseEntity.ok().body(social);
    }

    /*
    페이징*/
    @GetMapping("/size")
    public ResponseEntity<?> socialSize() {
        List<Social> socialList = socialService.readAllSocialSize();
        return ResponseEntity.ok().body(socialList.size());
    }


    /*
    검색 기능*/
    @GetMapping("/search")
    public ResponseEntity<List<?>> socialSearch(@RequestParam(name = "socialName") String socialName, final Pageable pageable) {

        System.out.println(socialName + ": 확인");

        List<SocialDTO> socialDTOList = socialService.findSocialBySocialNameContaining(pageable, socialName);

        if (socialDTOList.size() == 0) {
            List<String> error = new ArrayList<>();
            error.add(null);
            return ResponseEntity.status(500).body(error);
        } else {
            return ResponseEntity.ok().body(socialDTOList);
        }
    }

    @GetMapping("/search/size")
    public ResponseEntity<?> socialSearchSize(@RequestParam(name = "socialName") String socialName) {
        List<Social> socialList = socialService.findBySocialNameContaining(socialName);

        if (socialList.size() == 0) {
            List<String> error = new ArrayList<>();
            error.add(null);
            return ResponseEntity.status(500).body(error);
        } else {
            return ResponseEntity.ok().body(socialList.size());
        }
    }
}
