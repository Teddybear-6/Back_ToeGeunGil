package com.teddybear6.toegeungil.qna.controller;

import com.teddybear6.toegeungil.qna.entity.Answer;
import com.teddybear6.toegeungil.qna.service.AnsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    private final AnsService ansService;
    public AnswerController(AnsService ansService) {
        this.ansService = ansService;
    }

    @GetMapping("/{ansNum}")
    public ResponseEntity<Object> findAnswerByCode(@PathVariable int ansNum){
        Answer answer = ansService.findAnswerByCode(ansNum);

        if(Objects.isNull(answer)){
            return ResponseEntity.status(404).body(new String("다시 입력"));
        }
        return ResponseEntity.ok().body(answer);
    }

    @GetMapping("/list")
    public ResponseEntity<List<?>> findAllAnswer(){
        List<Answer> answerList = ansService.findAllAnswer();
        if(answerList.size() < 0){
            List<String > error = new ArrayList<>();
            error.add("String");
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok().body(answerList);
    }
    @PostMapping("/regist")
    public ResponseEntity<?> regist(Answer answer){
        System.out.println("cnt: " + answer);
        int result = ansService.registAnswer(answer);

        return ResponseEntity.ok().body("성공");
    }  //글 생성

    @PutMapping("/update")
    public ResponseEntity<?> update(Answer answer){
        Answer findAnswer = ansService.findAnswerByCode(answer.getAnswerNum());

        if(Objects.isNull(findAnswer)){
            return ResponseEntity.ok().body("데이터가 존재하지 않다.");
        }
        int result = ansService.updateAnswer(findAnswer, answer);
        if(result > 0){
            return  ResponseEntity.ok().body("수정 완료!");
        }else {
            return ResponseEntity.status(400).body("수정 실패");
        }
    }

    @DeleteMapping("/{delete}")
    public ResponseEntity<?> delete(@PathVariable int delete){
        ansService.deleteCode(delete);
        return ResponseEntity.ok().body("삭제 완료");
    }
}
