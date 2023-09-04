package com.teddybear6.toegeungil.community.service;

import com.teddybear6.toegeungil.community.entity.Community;
import com.teddybear6.toegeungil.community.repository.CommunityRepository;
import org.springframework.stereotype.Service;

@Service
public class CommunityService {
    private final CommunityRepository communityRepository;

    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public Community findByCommunityCode(int communityCode) {
        Community community = communityRepository.findById(communityCode);
        return community;
    }

}
