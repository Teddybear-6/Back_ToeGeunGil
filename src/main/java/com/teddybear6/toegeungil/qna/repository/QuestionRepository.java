package com.teddybear6.toegeungil.qna.repository;

import com.teddybear6.toegeungil.qna.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findById(int questionNum);
}