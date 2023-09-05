package com.teddybear6.toegeungil.qna.service;

import com.teddybear6.toegeungil.qna.entity.Question;
import com.teddybear6.toegeungil.qna.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class QnaService {
    private final QuestionRepository questionRepository;

    public QnaService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public Question findQuestionByCode(int questionCode){
        Question question = questionRepository.findById(questionCode);
        return question;

    }
    public List<Question> findAllQuestion(){
        List<Question> questionList = questionRepository.findAll();
        return questionList;
    }

    @Transactional
    public int registQuestion(Question menu) {

        Question result = questionRepository.save(menu);
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
            System.out.println("변경한 제목 : " + findQuestion.getQuestionTitle());
            System.out.println("변경한 내용 : " + findQuestion.getQuestionContent());

        }
        Question result = questionRepository.save(findQuestion);
        if(Objects.isNull(result)){
            return 0;
        }else {
            return 1;
        }
    }
}
