package com.teddybear6.toegeungil.social.repository;

import com.teddybear6.toegeungil.social.entity.Social;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialRepository extends JpaRepository<Social, Integer> {
    Social findById(int socialNum); //02_소셜 부분 조회(/social/{socialNum})

    List<Social> findByCategoryCode(int categoryCode); //30_카테고리 필터

    List<Social> findByLocalCode(int localCode); //31_지역 필터
}