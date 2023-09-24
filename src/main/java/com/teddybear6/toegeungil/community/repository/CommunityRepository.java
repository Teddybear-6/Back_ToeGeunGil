package com.teddybear6.toegeungil.community.repository;

import com.teddybear6.toegeungil.community.dto.CommunityDTO;
import com.teddybear6.toegeungil.community.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Integer> {

    Community findById(int communityNum);

    List<CommunityDTO> findByCategoryCode(Integer categoryCode);


    List<CommunityDTO> findByCategoryCodeAndLocalCode(Integer categoryCode, Integer localCode);

    List<CommunityDTO> findByLocalCode(Integer localCode);
}
