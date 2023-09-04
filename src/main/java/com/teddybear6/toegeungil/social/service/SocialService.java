package com.teddybear6.toegeungil.social.service;

import com.teddybear6.toegeungil.social.entity.Social;
import com.teddybear6.toegeungil.social.repository.SocialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialService {

    private final SocialRepository socialRepository;

    public SocialService(SocialRepository socialRepository) {
        this.socialRepository = socialRepository;
    }

    public List<Social> readAllSocial() {
        List<Social> socialList = socialRepository.findAll();
        return socialList;
    }
}
