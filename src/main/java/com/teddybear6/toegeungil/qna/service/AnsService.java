package com.teddybear6.toegeungil.qna.service;

import com.teddybear6.toegeungil.qna.entity.Answer;
import com.teddybear6.toegeungil.qna.entity.Question;
import com.teddybear6.toegeungil.qna.repository.AnswerRepository;
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
    public AnsService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
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
        Answer result = answerRepository.save(answer);
        System.out.println(result);

        answer.setAnswerDate(new Date());

        if(Objects.isNull(result)){
            return 0;
        }else {
            return 1;
        }
    }

    @Transactional
    public int updateAnswer(Answer findAnswer, Answer updateAnswer){
        if(!Objects.isNull(updateAnswer.getAnswerTitle())){
            findAnswer.setAnswerTitle(updateAnswer.getAnswerTitle());
            findAnswer.setAnswerContent(updateAnswer.getAnswerContent());

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
        answerRepository.deleteById(del);

        Answer answer = answerRepository.findById(del);
        System.out.println(answer);
    }


    public Answer findAnswerByQueNum(int quenum) {
        Answer answer= answerRepository.findByquestionNum(quenum);

        return answer;
    }
}