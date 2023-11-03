package com.teddybear6.toegeungil.qna.service;

import com.teddybear6.toegeungil.qna.entity.Answer;
import com.teddybear6.toegeungil.qna.entity.Question;
import com.teddybear6.toegeungil.qna.repository.AnswerRepository;
import com.teddybear6.toegeungil.qna.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;

//답변 서비스
@Service
public class AnsService {

    //answer 레파지토리
    private final AnswerRepository answerRepository;

    private final QuestionRepository questionRepository;

    public AnsService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    public Answer findAnswerByCode(int answerNum){
        Answer answer = answerRepository.findById(answerNum);
        return answer;
    }

    public List<Answer> findAllAnswer(){
        List<Answer> answerList = answerRepository.findAll();
        return answerList;
    }

    //답변 글 생성 로직
    @Transactional
    public int registAnswer(Answer answer){
        answer.setAnswerStatus("Y");
        answer.setAnswerDate(new Date());
        Answer result = answerRepository.save(answer);



        if(Objects.isNull(result)){
            return 0;
        }else {
            Question question = questionRepository.findById(answer.getQuestionNum());
            question.setAnswerStatus("Y");
            questionRepository.save(question);
            return 1;
        }
    }

    @Transactional
    public int updateAnswer(Answer findAnswer, Answer updateAnswer){
        if(!Objects.isNull(updateAnswer.getAnswerTitle())){
            findAnswer.setAnswerTitle(updateAnswer.getAnswerTitle());
            findAnswer.setAnswerContent(updateAnswer.getAnswerContent());
            findAnswer.setAnswerUpdate(new Date());

            System.out.println("변경한 답변 제목 : " + findAnswer.getAnswerTitle());
            System.out.println("변경한 답변 내용 : " + findAnswer.getAnswerContent());
        }
        Answer result = answerRepository.save(findAnswer);
        if(Objects.isNull(result)){
            return 0;
        }else {
            return 1;
        }

    }
    @Transactional
    public void deleteCode(int del){
        Answer answer = answerRepository.findById(del);
        answer.setAnswerStatus("N");
        answerRepository.save(answer);


    }


    public Answer findAnswerByQueNum(int quenum) {
        Answer answer= answerRepository.findByquestionNum(quenum);

        return answer;
    }
}