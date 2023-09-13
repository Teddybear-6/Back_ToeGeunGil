package com.teddybear6.toegeungil.hobby.repository;

import com.teddybear6.toegeungil.hobby.entity.HobbyKeyword;
import com.teddybear6.toegeungil.hobby.entity.HobbyPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyKeywordRepository extends JpaRepository<HobbyKeyword, HobbyPk> {
}
