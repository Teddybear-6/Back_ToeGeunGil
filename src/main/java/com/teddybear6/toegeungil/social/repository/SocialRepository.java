package com.teddybear6.toegeungil.social.repository;

import com.teddybear6.toegeungil.social.entity.Social;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialRepository extends JpaRepository<Social, Integer> {
    List<Social> findAllByOrderBySocialNumDesc(Pageable pageable);

    Social findById(int socialNum); //02_소셜 부분 조회(/social/{socialNum})

    List<Social> findByCategoryCode(int categoryCode, Pageable pageable); //30_카테고리 필터

    List<Social> findByLocalCode(int localCode); //31_지역 필터

    List<Social> findByCategoryCodeAndLocalCode(int categoryCode, int localCode); //32_카테고리 AND 지역

    List<Social> findSocialBySocialNameContaining(String socialName, Pageable pageable); //검색기능

    List<Social> findBySocialNameContaining(String socialName); //검색기능_size
}
