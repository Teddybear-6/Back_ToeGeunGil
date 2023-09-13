package com.teddybear6.toegeungil.community.service;

import com.teddybear6.toegeungil.community.dto.CommunityDTO;
import com.teddybear6.toegeungil.community.dto.CommunityKeywordDTO;
import com.teddybear6.toegeungil.community.entity.Community;
import com.teddybear6.toegeungil.community.entity.CommunityKeyword;
import com.teddybear6.toegeungil.community.entity.CommunityPK;
import com.teddybear6.toegeungil.community.repository.CommunityKeywordRepository;
import com.teddybear6.toegeungil.community.repository.CommunityRepository;
import com.teddybear6.toegeungil.keyword.entity.Keyword;
import com.teddybear6.toegeungil.keyword.repository.KeywordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final CommunityKeywordRepository communityKeywordRepository;

    private final KeywordRepository keywordRepository;


    public CommunityService(CommunityRepository communityRepository, CommunityKeywordRepository communityKeywordRepository, KeywordRepository keywordRepository) {
        this.communityRepository = communityRepository;
        this.communityKeywordRepository = communityKeywordRepository;
        this.keywordRepository = keywordRepository;
    }

    // 커뮤니티 전체 조회하기
    public List<Community> findAllCommunity() {

        List<Community> communityList = communityRepository.findAll();

        return communityList;
    }

    // 커뮤니티 글 찾기 (커뮤니티 번호로)
    public Community findByCommunityCode(int communityNum) {
        Community community = communityRepository.findById(communityNum);
        return community;
    }
    @Transactional
    public int registCommunity(CommunityDTO communityDTO) throws IOException {
        System.out.println("ddddd");
        Community community = new Community(communityDTO);
        List<CommunityKeywordDTO> keyword = communityDTO.getCommunityKeywordDTOList();
        List<CommunityKeyword> communityKeywordList = new ArrayList<>();
        System.out.println(keyword.size());
        for(int i = 0; i < keyword.size(); i++){
            /*
            * 1. 여기서 CommunityKeywordRepository 가 아니라 keywordRepository의 findById  를 사용해야해요
            * 2. 커뮤니티 테이블에 keywordNum 이라는 컬럼이 남아 있어서 문제였어
            *
            * */
            Keyword findKeyword = keywordRepository.findById(keyword.get(i).getKeywordCode());
            System.out.println("d");
            communityKeywordList.add(new CommunityKeyword(new CommunityPK(community.getCommunityNum(), findKeyword.getKeywordCode()), community, findKeyword));
            System.out.println("ssss");
        }
        System.out.println(communityKeywordList);
        community.setCommunityKeywordList(communityKeywordList);

        community.setPostWriteDate(new Date());

        Community findCommunity = communityRepository.save(community);

        if (Objects.isNull(findCommunity)) {
            return 0;
        } else {
            return 1;
        }
    }


    // 커뮤니티 글 수정하기
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

    // 커뮤니티 글 삭제하기
    @Transactional
    public int deleteCommunityId(int communityNum) {

        Community community = communityRepository.findById(communityNum);

        if(community == null){
            return 0;
        }

        communityRepository.delete(community);
        return 1;
    }

    //커뮤니티 검색 필터 (지역, 카테고리로 조회하기)
    public List<CommunityDTO> CommunityListFilters(Integer categoryNum, Integer locationNum) {

        List<CommunityDTO> communityList = new ArrayList<>();

        if (categoryNum != null && locationNum != null){
            communityList = communityRepository.findByCategoryNumAndLocationNum(categoryNum, locationNum);
        } else if (categoryNum != null) {
            communityList = communityRepository.findByCategoryNum(categoryNum);
        } else if(locationNum != null) {
            communityList = communityRepository.findByLocationNum(locationNum);
        }

        return communityList;
    }
    // Integer -> null , int -> null x
}
