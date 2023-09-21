package com.teddybear6.toegeungil.hobby.controller;


import com.teddybear6.toegeungil.auth.dto.AuthUserDetail;

import com.teddybear6.toegeungil.hobby.dto.*;
import com.teddybear6.toegeungil.hobby.entity.*;
import com.teddybear6.toegeungil.hobby.service.HobbyService;


import com.teddybear6.toegeungil.keyword.entity.Keyword;

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


@RestController
@RequestMapping("/hobbys")
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
     */

    // 취미 조회 -> 취미 대표사진 조회 - 디테일 보기 조회 -> 저장된 취미 사진 id 조회  -> id로 사진 조회


    private final HobbyService hobbyService;
    private final UserViewService userViewService;

    public HobbyController(HobbyService hobbyService, UserViewService userViewService) {
        this.hobbyService = hobbyService;
        this.userViewService = userViewService;
    }

    //findall
    @GetMapping
    public ResponseEntity<List<?>> hobbyfindAll(final Pageable pageable) {
        List<HobbyGetDTO> hobbyList = hobbyService.findAll(pageable);
        if (hobbyList.size() == 0) {
            List<String> error = new ArrayList<>();
            error.add("취미가 존재하지 않습니다.");
            return ResponseEntity.status(500).body(error);
        }
        return ResponseEntity.ok().body(hobbyList);
    }

    //취미 메인사진 조사
    @GetMapping("/mainimages/{hobbyCode}")
    public ResponseEntity<?> hobbyMianImage(@PathVariable int hobbyCode) {
        List<HobbyImage> hobbyImages = hobbyService.findMainImage(hobbyCode);


        if (hobbyImages.size() == 0) {
            //나중에 기본이미지로 바꾸기 무조건 하나씩 넣기하면 필요없음
            return ResponseEntity.status(404).body(null);
        }
        ImageIdDTO imageIdDTO = new ImageIdDTO();
        imageIdDTO.setHobbyCode(hobbyCode);
        imageIdDTO.setId(hobbyImages.get(0).getId());
        imageIdDTO.setPath(hobbyImages.get(0).getPath());

        return ResponseEntity.ok().body(imageIdDTO);
    }

    //등록
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','TUTOR')")
    public ResponseEntity<?> registHobby(@RequestPart(value = "hobby") HobbyDTO hobbyDTO, @RequestPart(value = "hobbyImage", required = false) MultipartFile[] files, @AuthenticationPrincipal AuthUserDetail userDetails) {

        UserEntity userEntity = userViewService.findUserEmail(userDetails.getUserEntity().getUserEmail());
        Map<String, String> respose = new HashMap<>();
        if (Objects.isNull(userEntity)) {
            respose.put("value", "회원이 아닙니다.");
            return ResponseEntity.status(500).body(respose);
        }


        System.out.println(userEntity);

        int result = 0;
        try {
            hobbyDTO.setTutorCode(userEntity.getUserNo());
            result = hobbyService.registHobby(hobbyDTO, files);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if (result > 0) {
            respose.put("value", "등록 성공했습니다.");
            return ResponseEntity.ok().body(respose);
        } else {
            respose.put("value", "등록 실패했습니다.");
            return ResponseEntity.status(500).body("등록 실패했습니다.");
        }

    }
    //수정
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAnyRole('ADMIN','TUTOR')")
    @PutMapping("/{hobbyCode}")
    public ResponseEntity<?> updateHobby(@PathVariable int hobbyCode, @RequestPart("hobby") HobbyDTO hobbyDTO, @RequestPart("hobbyImage") MultipartFile[] files , @AuthenticationPrincipal AuthUserDetail userDetails) {
        UserEntity userEntity = userViewService.findUserEmail(userDetails.getUserEntity().getUserEmail());
        Map<String, String> respose = new HashMap<>();


        if (Objects.isNull(userEntity)) {
            respose.put("value", "회원이 아닙니다.");
            return ResponseEntity.status(500).body(respose);
        }

        Hobby hobby = hobbyService.findById(hobbyCode);

        if (Objects.isNull(hobby)) {
            respose.put("value", "존재하지 않는 취미입니다..");
            return ResponseEntity.status(404).body(respose);
        }

        if(hobby.getTutorCode()!=userEntity.getUserNo()){
            respose.put("value", "작성자가 아닙니다.");
            return ResponseEntity.status(500).body(respose);
        }




        int result = hobbyService.updateHobby(hobby, hobbyDTO, files);
        if (result > 0) {
            respose.put("value", "수정 성공했습니다.");
            return ResponseEntity.ok().body(respose);
        } else {
            respose.put("value", "수정 실패했습니다.");
            return ResponseEntity.status(500).body(respose);
        }

    }


    //삭제
    @DeleteMapping("/{hobbyCode}")
    public ResponseEntity<?> hobbyDelete(@PathVariable int hobbyCode) {
        Hobby hobby = hobbyService.findById(hobbyCode);
        if (Objects.isNull(hobby)) {
            return ResponseEntity.status(404).body("존재하지 않는 취미입니다.");
        }
        int result = hobbyService.deleteById(hobby);

        if (result > 0) {
            return ResponseEntity.ok().body("삭제되었습니다.");
        } else {
            return ResponseEntity.status(500).body("삭제에 실패했습니다");
        }
    }

    //디테일보기

    @GetMapping("/{hobbyCode}")
    public ResponseEntity<Object> detailFindById(@PathVariable int hobbyCode) {

        Hobby hobby = hobbyService.findById(hobbyCode);

        if (Objects.isNull(hobby)) {
            return ResponseEntity.status(404).body("취미가 없습니다.");
        }

        HobbyDTO hobbyDTO = new HobbyDTO(hobby);
        List<Keyword> keyword = new ArrayList<>();
        for (int i = 0; i < hobby.getHobbyKeywordList().size(); i++) {
            keyword.add(hobby.getHobbyKeywordList().get(i).getKeyword());
        }
        List<HobbyImage> hobbyImages = hobby.getHobbyImages();

        List<ImageIdDTO> imageIdDTOS = new ArrayList<>();

        for (int i = 0; i < hobbyImages.size(); i++) {

            imageIdDTOS.add(new ImageIdDTO(hobbyImages.get(i).getId(), hobbyImages.get(i).getPath(), hobbyImages.get(i).getName(), hobbyImages.get(i).getHobbyCode()));

        }

        List<HobbyKeywordDTO> hobbyKeywordDTO = keyword.stream().map(m -> new HobbyKeywordDTO(m)).collect(Collectors.toList());
        hobbyDTO.setKeywordDTOList(hobbyKeywordDTO);
        hobbyDTO.setImageId(imageIdDTOS);
        return ResponseEntity.ok().body(hobbyDTO);


    }

    @GetMapping("/size")
    public ResponseEntity<?> hobbySize() {
        List<Hobby> hobbyList = hobbyService.findByAll();
        return ResponseEntity.ok().body(hobbyList.size());
    }


    //디테일 사진보기
//    @GetMapping("/image/{imageId}")
//    public ResponseEntity<?> detailImage(@PathVariable int imageId) {
//
//        HobbyImage image = hobbyService.detailImage(imageId);
//        return ResponseEntity.ok().contentType(MediaType.valueOf(image.getType())).body(ImageUtils.decompressImage(image.getImageDate()));
//    }


//    @GetMapping("/image/{hobbyCode}")
//    public ResponseEntity<?> detailImage(@PathVariable int hobbyCode)  throws IOException {
//        List<String> encodedImages = hobbyService.findEncodedImages(hobbyCode);
//
//        return ResponseEntity.ok().body(encodedImages);
//    }
    //참여하기
    /* 포스트?
     * api 어떻게 하지
     * /참여하기 @RequestParam?   취미번호 , 회원정보?
     * ?
     * 참여하기 요청하면  매핑 테이블에 취미번호와 회원 번호 저장? 연관 관계는? 지금 유저 엔티티가 없다
     *
     *
     * 이미 있으면  회원 번호로 검색해서 삭제
     *
     * 취미 조회하고 없거나 마감했으면 문닫았다고 리턴 해주고
     * 있으면 이미 참여했는지 조회 없으면 저장해주고 참여하기 완료 됐다고 리턴
     * 있으면 삭제해주고 취소했다고 리턴 true ? false
     * 버튼 같은건 어쩌지
     *
     * join
     * */

    //참가하기
    @PostMapping("/join/{hobbyCode}/{userNo}")
    public ResponseEntity<?> joinHobby(@PathVariable int hobbyCode, @PathVariable int userNo) {

        Hobby hobby = hobbyService.findById(hobbyCode);
        if (hobby.getClose().equals("Y")) {
            return ResponseEntity.ok().body("마감되었습니다.");
        }

        HobbyJoin hobbyJoin = hobbyService.findJoin(hobbyCode, userNo);


        if (Objects.isNull(hobbyJoin)) {
            List<HobbyJoin> HobbyJoin = hobbyService.findAllJoin(hobbyCode);
            if (hobby.getMaxPersonnel() <= HobbyJoin.size()) {
                return ResponseEntity.ok().body("참가 인원을 초과했습니다.");
            }
            HobbyJoin join = new HobbyJoin();
            join.setHobbyCode(hobbyCode);
            join.setUserNo(userNo);
            int result = hobbyService.joinHobby(join);
            if (result > 0) {
                return ResponseEntity.ok().body("참가 완료되었습니다.");
            } else {
                return ResponseEntity.status(500).body("등록할 수 없습니다.");
            }

        }
        int resultUnJoin = hobbyService.unJoinHobby(hobbyJoin);
        if (resultUnJoin > 0) {
            return ResponseEntity.ok().body("참가 취소 되었습니다.");
        } else {
            return ResponseEntity.status(500).body("참가 취소 실패했습니다.");
        }


    }

    //참가여부
    @GetMapping("/join/{hobbyCode}/{userNo}")
    public ResponseEntity<?> join(@PathVariable int hobbyCode, @PathVariable int userNo) {
        HobbyJoin hobbyJoin = hobbyService.findJoin(hobbyCode, userNo);

        if (Objects.isNull(hobbyJoin)) {
            return ResponseEntity.ok().body(false);
        } else {
            return ResponseEntity.ok().body(true);
        }
    }

    //참여자 리스트
    @GetMapping("/joinuser/{hobbyCode}")
    public ResponseEntity<List<?>> joinList(@PathVariable int hobbyCode) {
        List<HobbyJoin> hobbyJoins = hobbyService.findByJoin(hobbyCode);

        if (hobbyJoins.size() == 0) {
            return ResponseEntity.ok().body(null);
        } else {
            return ResponseEntity.ok().body(hobbyJoins);
        }
    }


    //마감하기

    @PutMapping("/close/{hobbyCode}")
    public ResponseEntity<?> closeHobby(@PathVariable int hobbyCode) {
        int result = hobbyService.closeHobby(hobbyCode);

        if (result > 0) {
            return ResponseEntity.ok().body("마감처리 되었습니다.");
        } else {
            return ResponseEntity.status(404).body("존재하지 않는 취미 입니다.");
        }

    }

    //참가자 get요청
    /*
     * 취미번호로 참가자 조회하기
     * 참가자 회원번호 리턴
     *
     *
     *
     * */


    //찜하기
    /*
     * 참여하기랑 비슷한 로직
     * */


    //찜리스트 보기?

    //후기등록
    @PostMapping("/review/{hobbyCode}")
    @PreAuthorize("hasAnyRole('USER','ADMIN','TUTOR')")
    public ResponseEntity<?> hobbyReview(@PathVariable int hobbyCode, @RequestBody HobbyReviewDTO hobbyReviewDTO, @AuthenticationPrincipal AuthUserDetail userDetails) {
        System.out.println(hobbyReviewDTO);
        Hobby hobby = hobbyService.findById(hobbyCode);
        hobbyReviewDTO.setUserNo(userDetails.getUserEntity().getUserNo());
        HobbyJoin hobbyJoin = hobbyService.findJoin(hobbyCode, hobbyReviewDTO.getUserNo());
        if (Objects.isNull(hobby) || hobby.getClose().equals("N") || Objects.isNull(hobbyJoin)) {
            return ResponseEntity.status(500).body("후기를 작성할 수 없습니다.");
        }
        HobbyReview findHobbyReview = hobbyService.findByIdReview(hobbyCode, hobbyReviewDTO.getUserNo());

        if (!Objects.isNull(findHobbyReview) && findHobbyReview.getReviewStatus().equals("Y")) {

            return ResponseEntity.status(404).body("이미 작성하셨습니다.");
        }
        hobbyReviewDTO.setHobbyCode(hobbyCode);
        HobbyReview hobbyReview = new HobbyReview(hobbyReviewDTO);

        int result = hobbyService.registReview(hobbyReview);

        if (result > 0) {
            return ResponseEntity.ok().body("후기 등록 성공했습니다.");
        } else {
            return ResponseEntity.status(500).body("후기 등록 실패 했습니다.");
        }

    }

    //취미별 후기보기
    @GetMapping("/review/{hobbyCode}")
    public ResponseEntity<List<?>> hobbyReviewAll(@PathVariable int hobbyCode) {
        List<HobbyReview> hobbyReviews = hobbyService.findAllReview(hobbyCode);

        if (hobbyReviews.size() == 0) {
            List<String> error = new ArrayList<>();
            error.add("아직 후기가 없습니다.");
            return ResponseEntity.ok().body(error);
        }

        List<HobbyReviewDTO> hobbyReviewDTOS = hobbyReviews.stream().map(m -> new HobbyReviewDTO(m)).collect(Collectors.toList());
        return ResponseEntity.ok().body(hobbyReviewDTOS);

    }


    //후기 삭제
    @DeleteMapping("/review/{reviewCode}")
    public ResponseEntity<?> removeReview(@PathVariable int reviewCode) {
        HobbyReview hobbyReview = hobbyService.findByReviewCode(reviewCode);
        if (Objects.isNull(hobbyReview)) {
            return ResponseEntity.status(404).body("후기가 없습니다.");
        }
        hobbyReview.setReviewStatus("N");
        int result = hobbyService.deleteByReviewCode(hobbyReview);

        if (result > 0) {
            return ResponseEntity.ok().body("삭제 완료됐습니다.");
        } else {
            return ResponseEntity.status(404).body("삭제 실패했습니다.");
        }

    }

    //후기 수정
    @PutMapping("/review/{reviewCode}")
    public ResponseEntity<?> modifyReview(@PathVariable int reviewCode, @RequestBody HobbyReviewDTO hobbyReviewDTO) {
        hobbyReviewDTO.setReviewCode(reviewCode);
        HobbyReview hobbyReview = hobbyService.findByReviewCode(reviewCode);
        if (Objects.isNull(hobbyReview)) {
            return ResponseEntity.status(404).body("후기가 없습니다.");
        }

        int result = hobbyService.updateReview(hobbyReviewDTO);

        if (result > 0) {
            return ResponseEntity.ok().body("수정 성공했습니다.");
        } else {
            return ResponseEntity.status(500).body("수정 실패했습니다.");
        }


    }

    // 후기의 답변
    /*
     * 후기 조회 후기 번호를 물고 답변 작성? 1대1 작성하기, 보기 필요
     * 후기답변 번호 , 후기번호 , 회원번호, 강사번호 , 내용, 상태
     * 후기가 삭제 될 경우?
     * 답변만 삭제 될 경우?
     * 연관 관계는
     *
     * */
    @PostMapping("/review/answer/{reviewCode}")
    public ResponseEntity<?> reviewAnswer(@PathVariable int reviewCode, @RequestBody ReviewAnswerDTO reviewAnswerDTO) {
        HobbyReview hobbyReview = hobbyService.findByReviewCode(reviewCode);

        if (Objects.isNull(hobbyReview)) {
            return ResponseEntity.status(404).body("존재하지 않는 후기입니다.");
        }
        ReviewAnswer frindReviewAnswer = hobbyService.reviewAnswerFindByRevieCode(reviewCode);
        if (!Objects.isNull(frindReviewAnswer)) {
            return ResponseEntity.status(404).body("이미 작성된 후기입니다.");
        }


        reviewAnswerDTO.setReviewCode(reviewCode);


        ReviewAnswer reviewAnswer = hobbyService.registReviewAnswer(reviewAnswerDTO);


        if (!Objects.isNull(reviewAnswer)) {
            return ResponseEntity.ok().body("답변 등록 성공했습니다.");
        }
        return ResponseEntity.status(500).body("답변 등록에 실패했습니다.");
    }


    //후기 답변 보기
    @GetMapping("/review/answer/{reviewCode}")
    public ResponseEntity<?> reviewAnswerFind(@PathVariable int reviewCode) {

        ReviewAnswer reviewAnswer = hobbyService.reviewAnswerFindByRevieCode(reviewCode);
        if (!Objects.isNull(reviewAnswer)) {
            ReviewAnswerDTO newReviewAnswerDTO = new ReviewAnswerDTO()
                    .reviewAnswerCode(reviewAnswer.getReviewAnswerCode())
                    .reviewCode(reviewAnswer.getReviewCode()).tutorCode(reviewAnswer
                            .getTutorCode()).content(reviewAnswer.getContent()).reviewAnswerCode(reviewAnswer.getReviewAnswerCode()).builder();

            return ResponseEntity.ok().body(newReviewAnswerDTO);
        }
        return ResponseEntity.status(404).body(null);

    }

    //카테고리 별 취미 조회
    //localhost:8001/hobbys/category/1?page=0&size=5
    @GetMapping("/category/{categoryCode}")
    public ResponseEntity<List<?>> categoryHobby(@PathVariable int categoryCode, final Pageable pageable) {
        List<HobbyGetDTO> hobbies = hobbyService.findByCategoryCode(categoryCode, pageable);

        if (hobbies.size() == 0) {
            List<String> error = new ArrayList<>();
            error.add("해당되는 취미가 없습니다.");
            return ResponseEntity.status(404).body(error);
        }

        return ResponseEntity.ok().body(hobbies);
    }


    //지역별 취미 조회
    //localhost:8001/hobbys/local/1?page=0&size=5
    @GetMapping("/local/{localCode}")
    public ResponseEntity<List<?>> localHobby(@PathVariable int localCode, final Pageable pageable) {
        List<HobbyGetDTO> hobbyGetDTOS = hobbyService.findByLocalCode(localCode, pageable);

        if (hobbyGetDTOS.size() == 0) {
            List<String> error = new ArrayList<>();
            error.add("해당되는 취미가 없습니다.");
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok().body(hobbyGetDTOS);
    }


    //지역별 카테고리 취미 조회
    //localhost:8001/hobbys/loacal/1/category/1?page=0&size=5
    @GetMapping("/loacal/{localCode}/category/{categoryCode}")
    public ResponseEntity<List<?>> localAndCategoryFilter(@PathVariable int localCode, @PathVariable int categoryCode, final Pageable pageable) {

        if (localCode == 0) {
            List<HobbyGetDTO> hobbies = hobbyService.findByCategoryCode(categoryCode, pageable);
        }

        if (categoryCode == 0) {
            List<HobbyGetDTO> hobbies = hobbyService.findByLocalCode(localCode, pageable);
        }

        List<HobbyGetDTO> hobbies = hobbyService.findByCategoryCodeAndLocalCode(categoryCode, localCode, pageable);

        if (hobbies.size() == 0) {
            List<String> error = new ArrayList<>();
            error.add("해당되는 취미가 없습니다.");
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok().body(hobbies);
    }


}
