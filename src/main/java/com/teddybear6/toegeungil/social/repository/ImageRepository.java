package com.teddybear6.toegeungil.social.repository;

import com.teddybear6.toegeungil.social.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
//    Optional<Image> findByName(String fileName);
}
