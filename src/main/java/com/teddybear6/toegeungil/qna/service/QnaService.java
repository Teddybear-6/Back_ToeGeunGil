package com.teddybear6.toegeungil.qna.service;

import com.teddybear6.toegeungil.qna.entity.Question;
import com.teddybear6.toegeungil.qna.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;

//질문 서비스
@Service
public class QnaService {
    private final QuestionRepository questionRepository;

    public QnaService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }  //생성자


    //question레파지토리에 있는 코드로 찾기
    public Question findQuestionByCode(int questionNum){
        Question question = questionRepository.findById(questionNum);
        return question;

    }

    public List<Question> findAllQuestion(){
        List<Question> questionList = questionRepository.findAll();
        return questionList;
    }

    @Transactional
    public int registQuestion(Question question) {
        question.setQuestionStatus("Y");
        question.setAnswerStatus("N");
        question.setQuestionDate(new Date());
        Question result = questionRepository.save(question);
        System.out.println(result);



        if(Objects.isNull(result)){
            return 0;
        }else{
            return 1;
        }
    }

    @Transactional
    public int updateQuestion(Question findQuestion, Question updateQuestion){
        if(!Objects.isNull(updateQuestion.getQuestionTitle())){
            findQuestion.setQuestionTitle(updateQuestion.getQuestionTitle());
            findQuestion.setQuestionContent(updateQuestion.getQuestionContent());
            findQuestion.setQuestionUpdate(new Date());

        }
        Question result = questionRepository.save(findQuestion);


        if(Objects.isNull(result)){
            return 0;
        }else {
            return 1;
        }
    }

    @Transactional
    public void deleteCode(int del){
        Question question= questionRepository.findById(del);
        question.setQuestionDelete(new Date());
        question.setQuestionStatus("N");
        questionRepository.save(question);

    }
}