package com.teddybear6.toegeungil.keyword.controller;

import com.teddybear6.toegeungil.keyword.dto.KeywordDTO;
import com.teddybear6.toegeungil.keyword.entity.Keyword;
import com.teddybear6.toegeungil.keyword.service.KeywordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/keyword")
public class KeywordController {
    private final KeywordService keywordService;


    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }


    @GetMapping
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


    @PostMapping
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


}
