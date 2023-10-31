package com.teddybear6.toegeungil.qna.controller;

import com.teddybear6.toegeungil.qna.entity.Answer;
import com.teddybear6.toegeungil.qna.service.AnsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/answer")
@Api(value = "QnA 답변 Api", tags = {"05.1. QnA_Answer Info"}, description = "QnA_답변 Api")
@ApiResponses({
        @ApiResponse(code = 200,message = "성공"),
        @ApiResponse(code = 404,message = "잘못된 접근") ,
        @ApiResponse(code = 500,message = "서버에러")
})
public class AnswerController {

    private final AnsService ansService;
    public AnswerController(AnsService ansService) {
        this.ansService = ansService;
    }

    @GetMapping("/{ansNum}")
    @ApiOperation(value = "QnA 답변 단일 조회 Api", notes = "QnA 답변 번호로 해당 게시글을 조회한다.")
    public ResponseEntity<Object> findAnswerByCode(@PathVariable int ansNum){
        Answer answer = ansService.findAnswerByCode(ansNum);

        if(Objects.isNull(answer)){
            return ResponseEntity.status(404).body(new String("다시 입력"));
        }
        return ResponseEntity.ok().body(answer);
    }

    @GetMapping("/list")
    @ApiOperation(value = "QnA 답변 전체 조회 Api", notes = "QnA 답변 전체 목록을 조회한다.")
    public ResponseEntity<List<?>> findAllAnswer(){
        List<Answer> answerList = ansService.findAllAnswer();
        if(answerList.size() < 0){
            List<String > error = new ArrayList<>();
            error.add("String");
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok().body(answerList);
    }

    @GetMapping("/que/{quenum}")
    @ApiOperation(value = "QnA 답변 단일 조회 Api", notes = "QnA 번호로 해당 게시글을 조회한다.")
    public ResponseEntity<?> findAnswerByQueNUm(@PathVariable int quenum){
        Answer answer = ansService.findAnswerByQueNum(quenum);
        Map<String,Object> value = new HashMap<>();
        if(Objects.isNull(answer)){

            value.put("value",null);
            return ResponseEntity.ok().body(value);
        }
        value.put("value",answer);
        return ResponseEntity.ok().body(value);
    }





    @PostMapping("/regist")
    @ApiOperation(value = "QnA 답변 작성 Api", notes = "QnA 답변 게시글을 작성한다.")
    public ResponseEntity<?> regist(@RequestBody Answer answer){
        System.out.println("cnt: " + answer);
        int result = ansService.registAnswer(answer);

        return ResponseEntity.ok().body("성공");
    }  //글 생성

    @PutMapping("/update")
    @ApiOperation(value = "QnA 답변 수정 Api", notes = "QnA 답변 게시글을 수정한다.")
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
    @ApiOperation(value = "QnA 답변 삭제 Api", notes = "QnA 답변 게시글을 삭제한다.")
    public ResponseEntity<?> delete(@PathVariable int delete){
        ansService.deleteCode(delete);
        return ResponseEntity.ok().body("삭제 완료");
    }
}