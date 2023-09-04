package com.teddybear6.toegeungil.community.repository;

import com.teddybear6.toegeungil.community.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Integer> {

    Community findById(int communityCode);
}
