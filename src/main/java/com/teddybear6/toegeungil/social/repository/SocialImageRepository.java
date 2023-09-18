package com.teddybear6.toegeungil.social.repository;

import com.teddybear6.toegeungil.social.entity.SocialImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@NoRepositoryBean
//@Repository
public interface SocialImageRepository extends JpaRepository<SocialImage, Integer> {
}
