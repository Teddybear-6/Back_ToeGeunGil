package com.teddybear6.toegeungil.social.repository;

import com.teddybear6.toegeungil.social.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Integer> {
    Optional<File> findById(int fileNum);
}
