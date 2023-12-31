package com.teddybear6.toegeungil.hobby.controller;


import com.teddybear6.toegeungil.auth.dto.AuthUserDetail;

import com.teddybear6.toegeungil.hobby.dto.*;
import com.teddybear6.toegeungil.hobby.entity.*;
import com.teddybear6.toegeungil.hobby.service.HobbyService;


import com.teddybear6.toegeungil.keyword.entity.Keyword;

import com.teddybear6.toegeungil.user.entity.UserEntity;
import com.teddybear6.toegeungil.user.sevice.UserViewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Pageable;


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
@Api(value = "취미 Api", tags = {"01. Hobby Info"}, description = "취미 Api")
@ApiResponses({
        @ApiResponse(code = 200,message = "성공"),
        @ApiResponse(code = 404,message = "잘못된 접근") ,
        @ApiResponse(code = 500,message = "서버에러")
})

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
    @ApiOperation(value = "취미 전체 조회 Api", notes = "취미 전체 목록을 조회한다.")
    public ResponseEntity< Map<String, Object>> hobbyfindAll(final Pageable pageable) {
        Map<String, Object> allhobbyMap = hobbyService.findAll(pageable);
        List<HobbyGetDTO> hobbyGetDTOS = (List<HobbyGetDTO>) allhobbyMap.get("value");
        if (hobbyGetDTOS.size() == 0) {
            Map<String, Object> error = new HashMap<>();
            error.put("error",null);

            return ResponseEntity.status(500).body(error);
        }

        return ResponseEntity.ok().body(allhobbyMap);
    }

    @GetMapping("/tutor")
    @PreAuthorize("hasAnyRole('ADMIN','TUTOR')")
    @ApiOperation(value = "강사별 취미 조회 Api", notes = "강사별 소셜 목록을 조회한다.")
    public ResponseEntity< Map<String, Object>> hobbyfindTutor( @AuthenticationPrincipal AuthUserDetail userDetails, final Pageable pageable) {
        UserEntity userEntity = userViewService.findUserEmail(userDetails.getUserEntity().getUserEmail());

        if (Objects.isNull(userEntity)) {
            Map<String, Object> error = new HashMap<>();
            error.put("user","회원이 아닙니다.");
            return ResponseEntity.status(500).body(error);
        }

        Map<String, Object> tutorhobbyMap = hobbyService.findByTutorCode(pageable , userEntity.getUserNo() );
        List<HobbyGetDTO> hobbyGetDTOS = (List<HobbyGetDTO>) tutorhobbyMap.get("value");
        if (hobbyGetDTOS.size() == 0) {
            Map<String, Object> error = new HashMap<>();
            error.put("error",null);
            return ResponseEntity.status(500).body(error);
        }

        return ResponseEntity.ok().body(tutorhobbyMap);

    }


    //등록
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','TUTOR')")
    @ApiOperation(value = "취미 작성 Api", notes = "취미 게시글을 작성한다.")
    public ResponseEntity<?> registHobby(@RequestPart(value = "hobby") HobbyDTO hobbyDTO, @RequestPart(value = "hobbyImage", required = false) MultipartFile[] files, @AuthenticationPrincipal AuthUserDetail userDetails) {

        UserEntity userEntity = userViewService.findUserEmail(userDetails.getUserEntity().getUserEmail());
        Map<String, String> respose = new HashMap<>();
        if (Objects.isNull(userEntity)) {
            respose.put("value", "회원이 아닙니다.");
            return ResponseEntity.status(500).body(respose);
        }

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
            return ResponseEntity.status(500).body(respose);
        }

    }

    //수정
    @PreAuthorize("hasAnyRole('ADMIN','TUTOR')")
    @PutMapping
    @ApiOperation(value = "취미 수정 Api", notes = "취미 게시글을 수정한다.")
    public ResponseEntity<?> updateHobby( @RequestPart("hobby") HobbyDTO hobbyDTO, @RequestPart(value = "hobbyImage" ,required = false) MultipartFile[] files , @RequestPart(value = "urls",required = false) List<ImageUrlsDTO> urls  , @AuthenticationPrincipal AuthUserDetail userDetails ) {
        UserEntity userEntity = userViewService.findUserEmail(userDetails.getUserEntity().getUserEmail());
        Map<String, String> respose = new HashMap<>();

        if (Objects.isNull(userEntity)) {
            respose.put("value", "회원이 아닙니다.");
            return ResponseEntity.status(500).body(respose);
        }

        Hobby hobby = hobbyService.findById(hobbyDTO.getHobbyCode());

        if (Objects.isNull(hobby)) {
            respose.put("value", "존재하지 않는 취미입니다..");
            return ResponseEntity.status(404).body(respose);
        }

        if(hobby.getTutorCode()!=userEntity.getUserNo()){
            respose.put("value", "작성자가 아닙니다.");
            return ResponseEntity.status(404).body(respose);
        }

        int result = hobbyService.updateHobby(hobby, hobbyDTO, files , urls);

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
    @PreAuthorize("hasAnyRole('ADMIN','TUTOR')")
    @ApiOperation(value = "취미 삭제 Api", notes = "취미 게시글을 삭제한다.")
    public ResponseEntity<?> hobbyDelete(@PathVariable int hobbyCode,  @AuthenticationPrincipal AuthUserDetail userDetails ) {
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
            return ResponseEntity.status(404).body(respose);
        }

        int result = hobbyService.deleteById(hobby);

        if (result > 0) {
            respose.put("value", "삭제되었습니다.");
            return ResponseEntity.ok().body(respose);
        } else {
            respose.put("value", "삭제에 실패했습니다");
            return ResponseEntity.status(500).body(respose);
        }
    }

    //디테일보기
    @GetMapping("/{hobbyCode}")
    @ApiOperation(value = "취미 단일 조회 Api", notes = "취미 게시글 번호로 해당 게시글을 조회한다.")
    public ResponseEntity<Object> detailFindById(@PathVariable int hobbyCode) {

        HobbyDTO hobbyDTO = hobbyService.findByIddetail(hobbyCode);

        if (Objects.isNull(hobbyDTO)) {
            return ResponseEntity.status(404).body("취미가 없습니다.");
        }



        return ResponseEntity.ok().body(hobbyDTO);
    }



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
    @ApiOperation(value = "취미 참여 등록 Api", notes = "취미 게시글 번호와 유저 번호로 해당 게시글에 참여 등록한다.")
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
    @ApiOperation(value = "취미 참여 여부 조회 Api", notes = "취미 게시글 번호와 유저 번호로 해당 게시글의 참여 여부를 조회한다.")
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
    @ApiOperation(value = "취미 참여 회원 조회 Api", notes = "취미 게시글 번호로 해당 게시글을 참여 회원을 조회한다.")
    public ResponseEntity<List<?>> joinList(@PathVariable int hobbyCode) {
        List<HobbyJoin> hobbyJoins = hobbyService.findByJoin(hobbyCode);

        if (hobbyJoins.size() == 0) {
            return ResponseEntity.ok().body(hobbyJoins);
        } else {
            return ResponseEntity.ok().body(hobbyJoins);
        }
    }


    //마감하기
    @PutMapping("/close/{hobbyCode}")
    @ApiOperation(value = "취미 참여 마감 Api", notes = "취미 게시글 번호로 해당 게시글의 참여를 마감한다.")
    public ResponseEntity<?> closeHobby(@PathVariable int hobbyCode) {
        int result = hobbyService.closeHobby(hobbyCode);

        if (result > 0) {
            return ResponseEntity.ok().body("마감처리 되었습니다.");
        } else {
            return ResponseEntity.status(404).body("존재하지 않는 취미 입니다.");
        }

    }


    //찜하기



    //찜리스트 보기

    //후기등록
    @PostMapping("/review/{hobbyCode}")
    @PreAuthorize("hasAnyRole('USER','ADMIN','TUTOR')")
    @ApiOperation(value = "취미 후기 작성 Api", notes = "소셜 게시글 번호로 해당 게시글의 참여 후기를 작성한다.")
    public ResponseEntity<?> hobbyReview(@PathVariable int hobbyCode, @RequestBody HobbyReviewDTO hobbyReviewDTO, @AuthenticationPrincipal AuthUserDetail userDetails) {

        Hobby hobby = hobbyService.findById(hobbyCode);


        HobbyJoin hobbyJoin = hobbyService.findJoin(hobbyCode, userDetails.getUserEntity().getUserNo());
        if (Objects.isNull(hobby) || hobby.getClose().equals("N") || Objects.isNull(hobbyJoin)) {
            return ResponseEntity.status(500).body("후기를 작성할 수 없습니다.");
        }

        hobbyReviewDTO.setUserNo(userDetails.getUserEntity().getUserNo());
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
    @ApiOperation(value = "취미별 후기 조회 Api", notes = "소셜 게시글 번호로 해당 게시글의 참여 후기 목록을 조회한다.")
    public ResponseEntity<List<?>> hobbyReviewAll(@PathVariable int hobbyCode) {
        List<HobbyReview> hobbyReviews = hobbyService.findAllReview(hobbyCode);

        if (hobbyReviews.size() == 0) {
            List<String> error = new ArrayList<>();
            error.add(null);
            return ResponseEntity.ok().body(error);
        }

        List<HobbyReviewDTO> hobbyReviewDTOS = hobbyReviews.stream().map(m -> new HobbyReviewDTO(m)).collect(Collectors.toList());
        return ResponseEntity.ok().body(hobbyReviewDTOS);

    }


    //후기 삭제
    @PreAuthorize("hasAnyRole('USER','ADMIN','TUTOR')")
    @DeleteMapping("/review/{reviewCode}")
    @ApiOperation(value = "취미 후기 삭제 Api", notes = "취미 리뷰 번호로 해당 게시글의 참여 후기를 삭제한다.")
    public ResponseEntity<?> removeReview(@PathVariable int reviewCode, @AuthenticationPrincipal AuthUserDetail userDetails) {
        Map<String, String> respose = new HashMap<>();
        int result = 0;
        HobbyReview hobbyReview = hobbyService.findByReviewCode(reviewCode);
        if (Objects.isNull(hobbyReview)) {
            return ResponseEntity.status(404).body("후기가 없습니다.");
        }
        if(userDetails.getUserEntity().getUserNo()==hobbyReview.getUserNo()){
            hobbyReview.setReviewStatus("N");
            result  = hobbyService.deleteByReviewCode(hobbyReview);

        }

        if (result > 0) {
            respose.put("value","삭제되었습니다.");
            return ResponseEntity.ok().body(respose);
        } else {
            respose.put("value","삭제 실패했습니다..");
            return ResponseEntity.status(404).body(respose);
        }

    }

    //후기 수정
    @PutMapping("/review/{reviewCode}")
    @PreAuthorize("hasAnyRole('USER','ADMIN','TUTOR')")
    @ApiOperation(value = "취미 후기 수정 Api", notes = "취미 리뷰 번호로 해당 게시글의 참여 후기를 수정한다.")
    public ResponseEntity<?> modifyReview(@PathVariable int reviewCode, @RequestBody HobbyReviewDTO hobbyReviewDTO ,@AuthenticationPrincipal AuthUserDetail userDetails) {
        Map<String, String> respose = new HashMap<>();
        HobbyReview hobbyReview = hobbyService.findByReviewCode(reviewCode);
        if (Objects.isNull(hobbyReview)) {
            respose.put("value","후기가 없습니다.");
            return ResponseEntity.status(404).body(respose);
        }

        if (hobbyReview.getUserNo()!=userDetails.getUserEntity().getUserNo()) {
            respose.put("value","작성자가 아닙니다.");
            return ResponseEntity.status(404).body(respose);
        }

        int result = hobbyService.updateReview(hobbyReview,hobbyReviewDTO);

        if (result > 0) {
            respose.put("value","수정 성공했습니다.");
            return ResponseEntity.ok().body(respose);
        } else {
            respose.put("value","수정 실패했습니다.");
            return ResponseEntity.status(500).body(respose);
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
    @ApiOperation(value = "취미 후기 답변 작성 Api", notes = "취미 리뷰 번호로 해당 참여 후기의 답변을 작성한다.")
    public ResponseEntity<?> reviewAnswer(@PathVariable int reviewCode, @RequestBody ReviewAnswerDTO reviewAnswerDTO) {
        HobbyReview hobbyReview = hobbyService.findByReviewCode(reviewCode);

        if (Objects.isNull(hobbyReview)) {
            return ResponseEntity.status(404).body(null);
        }
        ReviewAnswer frindReviewAnswer = hobbyService.reviewAnswerFindByRevieCode(reviewCode);
        if (!Objects.isNull(frindReviewAnswer)) {
            return ResponseEntity.status(404).body("이미 작성된 후기입니다.");
            //message
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
    @ApiOperation(value = "취미 후기 답변 조회 Api", notes = "취미 리뷰 번호로 해당 참여 후기의 답변을 조회한다.")
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
    @ApiOperation(value = "카테고리별 취미 조회 Api", notes = "카테고리 번호로 카테고리별 해당 취미 게시글 목록을 조회한다.")
    public ResponseEntity<Map<String,Object>> categoryHobby(@PathVariable int categoryCode, final Pageable pageable) {
        Map<String,Object> categotyHoobby = hobbyService.findByCategoryCode(categoryCode,pageable);
        List<HobbyGetDTO> hobbies = (List<HobbyGetDTO>) categotyHoobby.get("value");

        if (hobbies.size() == 0) {
            Map<String,Object> error = new HashMap<>();
            error.put("error",null);
            return ResponseEntity.status(404).body(error);
        }

        return ResponseEntity.ok().body(categotyHoobby);
    }




    //지역별 취미 조회
    //localhost:8001/hobbys/local/1?page=0&size=5
    @GetMapping("/local/{localCode}")
    @ApiOperation(value = "지역별 취미 조회 Api", notes = "지역 번호로 지역별 해당 취미 게시글 목록을 조회한다.")
    public ResponseEntity<Map<String,Object>> localHobby(@PathVariable int localCode, final Pageable pageable) {
        Map<String,Object> localHobby = hobbyService.findByLocalCode(localCode, pageable);
        List<HobbyGetDTO> hobbyGetDTOS = (List<HobbyGetDTO>) localHobby.get("value");
        if (hobbyGetDTOS.size() == 0) {
            Map<String,Object> error = new HashMap<>();
            error.put("error",null);
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok().body(localHobby);
    }




    //지역별 카테고리 취미 조회
    //localhost:8001/hobbys/loacal/1/category/1?page=0&size=5
    @GetMapping("/loacal/{localCode}/category/{categoryCode}")
    @ApiOperation(value = "카테고리 AND 지역 취미 조회 Api", notes = "카테고리 번호와 지역 번호로 두 조건에 모두 해당되는 취미 게시글 목록을 조회한다.")
    public ResponseEntity<Map<String,Object>> localAndCategoryFilter(@PathVariable int localCode, @PathVariable int categoryCode, final Pageable pageable) {


        Map<String,Object> hobbies = hobbyService.findByCategoryCodeAndLocalCode(categoryCode, localCode, pageable);
        List<HobbyGetDTO> hobbyGetDTOS = (List<HobbyGetDTO>) hobbies.get("value");

        if (hobbyGetDTOS.size() == 0) {
            Map<String,Object> error = new HashMap<>();
            error.put("error",null);
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok().body(hobbies);
    }





    @GetMapping("/search")
    @ApiOperation(value = "취미 검색 Api", notes = "검색어를 통해 해당되는 취미의 제목을 조회한다.")
    public ResponseEntity<Map<String,Object>> hobbyfindsearch(final Pageable pageable,  @RequestParam(name="hobbytitle")  String hobbyTitle) {

        Map<String ,Object> searchHobby = hobbyService.findHobbyTitleContatining(pageable ,hobbyTitle);
        List<HobbyGetDTO> hobbyGetDTOS = (List<HobbyGetDTO>) searchHobby.get("value");
        if (hobbyGetDTOS.size() == 0) {
           Map<String ,Object> error = new HashMap<>();
            error.put("error",null);
            return ResponseEntity.status(500).body(error);
        }
        return ResponseEntity.ok().body(searchHobby);
    }


}