package com.teddybear6.toegeungil.community.service;

import com.teddybear6.toegeungil.community.dto.CommunityDTO;
import com.teddybear6.toegeungil.community.entity.Community;
import com.teddybear6.toegeungil.community.repository.CommunityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CommunityService {
    private final CommunityRepository communityRepository;

    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }


    public List<Community> findAllCommunity() {

        List<Community> communityList = communityRepository.findAll();

        return communityList;
    }

    public Community findByCommunityCode(int communityNum) {
        Community community = communityRepository.findById(communityNum);
        return community;
    }
    @Transactional
    public int registCommunity(CommunityDTO communityDTO) throws IOException {
        Community community = new Community(communityDTO);

        community.setPostWriteDate(new Date());

        Community findCommunity = communityRepository.save(community);

        if(Objects.isNull(findCommunity)){
            return 0;
        }else {
            return 1;
        }
    }

//    @Transactional
//    public int communityUpdate(Community findCommunity, String communityTitle, String communityIntro, int categoryNum, int keywordNum, int locationNum, String communityStatus) {
//
//        // 업데이트할 community 게시글이 있는지 확인한다.
//        if(findCommunity == null){
//            return 0;
//        }
//
//        // 매개 변수의 값으로 기존 커뮤니티의 필드를 업데이트한다.
//        findCommunity.setCommunityTitle(communityTitle);
//        findCommunity.setCommunityIntro(communityIntro);
//        findCommunity.setCategoryNum(categoryNum);
//        findCommunity.setKeywordNum(keywordNum);
//        findCommunity.setLocationNum(locationNum);
//        findCommunity.setCommunityStatus(communityStatus);
//        findCommunity.setPostUpdateDate(new Date()); // 수정 날짜 업데이트
//
//        // update된 community를 entity에 저장
//        Community updateCommunity = communityRepository.save(findCommunity);
//
//        if(updateCommunity != null) {
//            return 1;
//        } else{
//            return 0;
//        }
//    }

    @Transactional
    public int communityUpdate(Community findCommunity, CommunityDTO communityDTO) {

        // 업데이트할 community 게시글이 있는지 확인한다.
        if(findCommunity == null){
            return 0;
        }

        // 매개 변수의 값으로 기존 커뮤니티의 필드를 업데이트한다.
        findCommunity.setCommunityTitle(communityDTO.getCommunityTitle());
        findCommunity.setCommunityIntro(communityDTO.getCommunityIntro());
        findCommunity.setCategoryNum(communityDTO.getCategoryNum());
        findCommunity.setKeywordNum(communityDTO.getKeywordNum());
        findCommunity.setLocationNum(communityDTO.getLocationNum());
        findCommunity.setCommunityStatus(communityDTO.getCommunityStatus());
        findCommunity.setPostUpdateDate(new Date()); // 수정 날짜 업데이트

        // update된 community를 entity에 저장
        Community updateCommunity = communityRepository.save(findCommunity);

        if(updateCommunity != null) {
            return 1;
        } else{
            return 0;
        }
    }

    @Transactional
    public int deleteCommunityId(int communityNum) {

        Community community = communityRepository.findById(communityNum);

        if(community == null){
            return 0;
        }

        communityRepository.delete(community);
        return 1;
    }

}
