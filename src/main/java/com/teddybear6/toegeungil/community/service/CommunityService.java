package com.teddybear6.toegeungil.community.service;

import com.teddybear6.toegeungil.community.dto.CommunityDTO;
import com.teddybear6.toegeungil.community.entity.Community;
import com.teddybear6.toegeungil.community.repository.CommunityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Objects;

@Service
public class CommunityService {
    private final CommunityRepository communityRepository;

    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public Community findByCommunityCode(int communityNum) {
        Community community = communityRepository.findById(communityNum);
        return community;
    }
    @Transactional
    public int registCommunity(CommunityDTO communityDTO) throws IOException {
        Community community = new Community(communityDTO);

        Community findCommunity = communityRepository.save(community);

        if(Objects.isNull(findCommunity)){
            return 0;
        }else {
            return 1;
        }
    }
}
