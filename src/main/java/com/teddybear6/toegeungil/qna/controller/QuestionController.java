package com.teddybear6.toegeungil.qna.controller;

import com.teddybear6.toegeungil.qna.entity.Question;
import com.teddybear6.toegeungil.qna.service.QnaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QnaService qnaService;

    public QuestionController(QnaService qnaService) {
        this.qnaService = qnaService;
    }
    @GetMapping("/{queNum}")
    public ResponseEntity<Object> findQuestionByCode(@PathVariable int queNum){
        Question question = qnaService.findQuestionByCode(queNum);

        if(Objects.isNull(question)){
            return ResponseEntity.status(404).body(new String("똑바로 입력해라"));
        }
        return ResponseEntity.ok().body(question);
    }

    @GetMapping("/list")
    public ResponseEntity<List<?>> findAllQuestion(){
        List<Question> questionList = qnaService.findAllQuestion();
        if(questionList.size() < 0){
            List<String> error =  new ArrayList<>();
            error.add("String");
            return ResponseEntity.status(404).body(error);

        }
        return ResponseEntity.ok().body(questionList);
    }
    @PostMapping("/regist")
    public ResponseEntity<?> regist(Question question){
        System.out.println("cnt " + question);
        int result = qnaService.registQuestion(question);

        return ResponseEntity.ok().body("성공");
    }


    @PutMapping("/update")
    public ResponseEntity<?> update(Question question){
        Question findQuestion = qnaService.findQuestionByCode(question.getQuestionNum());

        if(Objects.isNull(findQuestion)){
            return ResponseEntity.ok().body("데이터가 존재하지 않습니다.");
        }
        int result = qnaService.updateQuestion(findQuestion, question);
        if(result > 0){
            return ResponseEntity.ok().body("수정 완료");
        }else {
            return ResponseEntity.status(400).body("수정 실패");
        }

    }

    @DeleteMapping("/{delete}")
    public ResponseEntity<?> delete(@PathVariable int delete){
        qnaService.deleteCode(delete);
        return ResponseEntity.ok().body("삭제 완료");
    }
}
