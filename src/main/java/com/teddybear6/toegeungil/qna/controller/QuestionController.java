package com.teddybear6.toegeungil.qna.controller;

import com.teddybear6.toegeungil.qna.entity.Question;
import com.teddybear6.toegeungil.qna.service.QnaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/question")
@Api(value = "QnA 질문 Api", tags = {"05. QnA_Question Info"}, description = "QnA_질문 Api")
@ApiResponses({
        @ApiResponse(code = 200,message = "성공"),
        @ApiResponse(code = 404,message = "잘못된 접근") ,
        @ApiResponse(code = 500,message = "서버에러")
})
public class QuestionController {

    private final QnaService qnaService;

    public QuestionController(QnaService qnaService) {
        this.qnaService = qnaService;
    }
    
    @GetMapping("/{queNum}") //단일 조회
    @ApiOperation(value = "QnA 질문 단일 조회 Api", notes = "QnA 질문 번호로 해당 게시글을 조회한다.")
    public ResponseEntity<Object> findQuestionByCode(@PathVariable int queNum){
        Question question = qnaService.findQuestionByCode(queNum);

        if(Objects.isNull(question)){
            return ResponseEntity.status(404).body(new String("똑바로 입력해라"));
        }
        return ResponseEntity.ok().body(question);
    }

    @GetMapping("/list") //전체 조회
    @ApiOperation(value = "QnA 질문 전체 조회 Api", notes = "QnA 질문 전체 목록을 조회한다.")
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
    @ApiOperation(value = "QnA 질문 작성 Api", notes = "QnA 질문 게시글을 작성한다.")
    public ResponseEntity<?> regist(@RequestBody Question question){
        System.out.println("cnt " + question);
        int result = qnaService.registQuestion(question);

        return ResponseEntity.ok().body("성공");
    }


    @PutMapping("/update")
    @ApiOperation(value = "QnA 질문 수정 Api", notes = "QnA 질문 게시글을 수정한다.")
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
    @ApiOperation(value = "QnA 질문 삭제 Api", notes = "QnA 질문 게시글을 삭제한다.")
    public ResponseEntity<?> delete(@PathVariable int delete){
        qnaService.deleteCode(delete);
        return ResponseEntity.ok().body("삭제 완료");
    }
}