package com.teddybear6.toegeungil.hobby.repository;

import com.teddybear6.toegeungil.hobby.entity.HobbyReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HobbyReviewRepository extends JpaRepository<HobbyReview,Integer> {
    List<HobbyReview> findAllByHobbyCode(int hobbyCode);
    HobbyReview findById(int reviewCode);

    HobbyReview findAllByHobbyCodeAndUserNo(int hobbyCode, int userNo);
}
