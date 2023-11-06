package com.teddybear6.toegeungil.community.repository;

import com.teddybear6.toegeungil.community.dto.CommunityDTO;
import com.teddybear6.toegeungil.community.entity.Community;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Integer> {

    Community findById(int communityNum);

    List<Community> findByCategoryCode(int categoryCode, Pageable pageable);

    List<Community> findByLocalCode(int localCode, Pageable pageable);

    List<Community> findByCategoryCodeAndLocalCode(int categoryCode, int localCode);

    List<Community> findCommunityByCommunityTitleContaining(String communityTitle, Pageable pageable);
}
