package com.teddybear6.toegeungil.social.repository;

import com.teddybear6.toegeungil.social.entity.Social;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialRepository extends JpaRepository<Social, Integer> {
    Social findById(int socialNum); //02_소셜 부분 조회(/social/{socialNum})
}
