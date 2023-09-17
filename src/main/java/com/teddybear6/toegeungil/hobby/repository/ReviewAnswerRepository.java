package com.teddybear6.toegeungil.hobby.repository;

import com.teddybear6.toegeungil.hobby.entity.ReviewAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewAnswerRepository  extends JpaRepository<ReviewAnswer ,Integer> {
    ReviewAnswer findAllByReviewCode(int reviewCode);
}
