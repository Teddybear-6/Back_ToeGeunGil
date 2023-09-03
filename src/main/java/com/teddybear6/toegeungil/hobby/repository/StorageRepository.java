package com.teddybear6.toegeungil.hobby.repository;

import com.teddybear6.toegeungil.hobby.entity.HobbyImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository  extends JpaRepository<HobbyImage,Integer> {

    Optional<HobbyImage> findByName(String fileName);
}
