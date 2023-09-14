package com.teddybear6.toegeungil.qna.repository;

import com.teddybear6.toegeungil.qna.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    Answer findById(int answerNum);


}