package com.teddybear6.toegeungil.hobby.controller;

import com.teddybear6.toegeungil.hobby.dto.HobbyDTO;
import com.teddybear6.toegeungil.hobby.dto.HobbyGetDTO;
import com.teddybear6.toegeungil.hobby.dto.HobbyKeywordDTO;
import com.teddybear6.toegeungil.hobby.entity.Hobby;
import com.teddybear6.toegeungil.hobby.service.HobbyService;


import com.teddybear6.toegeungil.keyword.entity.Keyword;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.zip.Deflater;

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
     *
     * 1.리드 부터 해보자
     * 전체 조회는 사진 제목 가격 카테고리 키워드만 보이면 된다
     */

    private final HobbyService hobbyService;

    public HobbyController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }


    //이미지테스트 파일을 db에 저장
    @PostMapping("/images")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = hobbyService.uploadImage(file);
        return ResponseEntity.ok().body(uploadImage);
    }

    //이미지 다운로드
    @GetMapping("/images/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable("fileName") String fileName) {
        System.out.println(fileName);

        byte[] downloadImage = hobbyService.downloadImage(fileName);
        return ResponseEntity.ok().contentType(MediaType.valueOf("image/png"))
                .body(downloadImage);
    }

    //findall
    @GetMapping
    public ResponseEntity<List<?>> hobbyfindAll(final Pageable pageable){
        List<HobbyGetDTO> hobbyList = hobbyService.findAll(pageable);
        if(hobbyList.size()==0){
            List<String> error = new ArrayList<>();
            error.add("취미가 존재하지 않습니다.");
            return ResponseEntity.status(500).body(error);
        }
        return ResponseEntity.ok().body(hobbyList);
    }

    //취미 메인사진 조사
    @GetMapping("/mainimages/{hobbyCode}")
    public  ResponseEntity<?> hobbyMianImage(@PathVariable int hobbyCode){
        byte[] mainimage = hobbyService.findMainImage(hobbyCode);

        if(mainimage.length==0){
            //나중에 기본이미지로 바꾸기
            return ResponseEntity.status(404).body("이미지를 찾을 수 없습니다.");
        }
        return ResponseEntity.ok().contentType(MediaType.valueOf("image/png")).body(mainimage);
    }

    //등록
    @PostMapping
    public ResponseEntity<?> registHobby(@RequestPart("hobbyDTO") HobbyDTO hobbyDTO, @RequestPart("hobbyImage") List<MultipartFile> files) {
        int result = 0;
        try {
            result = hobbyService.registHobby(hobbyDTO, files);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (result > 0) {
            return ResponseEntity.ok().body("등록 성공했습니다.");
        } else {
            return ResponseEntity.status(500).body("등록에 실패했습니다");
        }

    }


    //수정



    //삭제


    //디테일보기

    @GetMapping("/{hobbyCode}")
    public ResponseEntity<Object> detailFindById(@PathVariable int hobbyCode){

        Hobby hobby = hobbyService.findById(hobbyCode);

        if(Objects.isNull(hobby)){
            return ResponseEntity.status(404).body("취미가 없습니다.");
        }
        System.out.println(hobby.getHobbyKeywordList());
        HobbyDTO hobbyDTO = new HobbyDTO(hobby);
        List<Keyword> keyword = new ArrayList<>();
        for(int i=0;i<hobby.getHobbyKeywordList().size();i++){
            keyword.add(hobby.getHobbyKeywordList().get(i).getKeyword());
        }
        List<HobbyKeywordDTO> hobbyKeywordDTO = keyword.stream().map(m->new HobbyKeywordDTO(m)).collect(Collectors.toList());
        hobbyDTO.setKeywordDTOList(hobbyKeywordDTO);
        return ResponseEntity.ok().body(hobbyDTO);


    }



}
