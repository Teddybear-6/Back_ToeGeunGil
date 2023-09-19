package com.teddybear6.toegeungil.social.repository;

import com.teddybear6.toegeungil.social.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByImageName(String imageName); //사진 다운로드
    Optional<Image> findByImageId(Long imageId); //사진 번호로 사진 다운로드
}
