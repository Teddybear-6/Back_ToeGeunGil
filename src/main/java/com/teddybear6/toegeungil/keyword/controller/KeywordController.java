package com.teddybear6.toegeungil.keyword.controller;

import com.teddybear6.toegeungil.keyword.dto.KeywordDTO;
import com.teddybear6.toegeungil.keyword.entity.Keyword;
import com.teddybear6.toegeungil.keyword.service.KeywordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/keyword")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "키워드 Api", tags = {"12. Keyword Info"}, description = "키워드 Api")
@ApiResponses({
        @ApiResponse(code = 200,message = "성공"),
        @ApiResponse(code = 404,message = "잘못된 접근") ,
        @ApiResponse(code = 500,message = "서버에러")
})
public class KeywordController {
    private final KeywordService keywordService;


    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }


    @GetMapping //키워드 전체 조회
    @ApiOperation(value = "키워드 전체 조회 Api", notes = "키워드 전체 목록을 조회한다.")
    public ResponseEntity<List<?>> findAllKeyword() {
        List<Keyword> keywordList = keywordService.findAll();

        if (keywordList.size() == 0) {
            List<String> error = new ArrayList<>();
            error.add("키워드가 존재하지 않습니다");
            return ResponseEntity.status(500).body(error);
        }
        List<KeywordDTO> keywordDTOS = keywordList.stream().map(m -> new KeywordDTO(m)).collect(Collectors.toList());

        return ResponseEntity.ok().body(keywordDTOS);
    }

    @GetMapping("/{keywordCode}") //단일 조회
    @ApiOperation(value = "키워드 단일 조회 Api", notes = "키워드 번호로 해당 키워드 목록을 조회한다.")
    public ResponseEntity<Object> findBykeywordCode(@PathVariable int keywordCode){
        Keyword keyword = keywordService.findById(keywordCode);

        if(Objects.isNull(keyword)){
            return ResponseEntity.status(404).body("존재하지 않는 키워드입니다.");
        }
        KeywordDTO keywordDTO = new KeywordDTO(keyword);

        return ResponseEntity.ok().body(keywordDTO);

    }


    @PostMapping //키워드 등록
    @ApiOperation(value = "키워드 등록 Api", notes = "새로운 키워드를 등록한다.")
    public ResponseEntity<?> registKeyword(KeywordDTO keywordDTO) {

        List<Keyword> keywordList = keywordService.findBykeywordName(keywordDTO.getKeywordName());

        if (keywordList.size() > 0) {
            return ResponseEntity.status(404).body("중복된 키워드입니다.");
        }

        Keyword keyword = new Keyword();
        keyword.setKeywordName(keywordDTO.getKeywordName());

        int result = keywordService.registKeyword(keyword);

        if (result > 0) {
            return ResponseEntity.ok().body("등록 성공했습니다.");
        } else {
            return ResponseEntity.status(500).body("등록에 실패했습니다.");
        }
    }


    @PutMapping("/{keywordCode}") //키워드 수정
    @ApiOperation(value = "키워드 수정 Api", notes = "키워드 번호로 해당 키워드 정보를 수정한다.")
    public  ResponseEntity<?> updateKeyword(@PathVariable int keywordCode , @RequestBody KeywordDTO keywordDTO){

        Keyword findkeyword = keywordService.findById(keywordCode);
        if(Objects.isNull(findkeyword)){
            return ResponseEntity.status(404).body("존재하지 않는 키워드 입니다.");
        }

        int result = keywordService.updatekeyword(findkeyword,keywordDTO);
        if (result>0){
            return ResponseEntity.ok().body("수정 성공했습니다.");
        }else {
            return ResponseEntity.status(500).body("수정 실패했습니다.");
        }
    }

    @DeleteMapping("/{keywordCode}") //키워드 삭제
    @ApiOperation(value = "키워드 삭제 Api", notes = "키워드 번호로 해당 키워드 정보를 삭제한다.")
    public ResponseEntity<?> deleteKeyword(@PathVariable int keywordCode){
        Keyword keyword = keywordService.findById(keywordCode);
        if(Objects.isNull(keyword)){
            return ResponseEntity.status(404).body("존재하지 않는 키워드 입니다.");
        }
        int result = keywordService.deleteById(keywordCode);

        if(result>0){
            return ResponseEntity.ok().body("삭제 되었습니다.");
        }else {
            return ResponseEntity.status(500).body("삭제되지 않았습니다.");
        }
    }

}