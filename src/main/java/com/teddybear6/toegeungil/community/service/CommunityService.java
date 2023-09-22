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
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public List<CommunityDTO> findAllCommunity() {
        List<Community> communityList = communityRepository.findAll();
        List<CommunityDTO> communityDTOList = communityList.stream().map(m -> new CommunityDTO(m)).collect(Collectors.toList());

        for (int i=0; i < communityList.size(); i++){
            List<Keyword> keywordList = new ArrayList<>();
            List<CommunityKeywordDTO> keywordDTOList = new ArrayList<>();
            for (int j=0; j < communityList.get(i).getCommunityKeywordList().size(); j++){
                keywordList.add(communityList.get(i).getCommunityKeywordList().get(j).getKeyword());
                keywordDTOList = keywordList.stream().map(m -> new CommunityKeywordDTO(m)).collect(Collectors.toList());
            }
            communityDTOList.get(i).setCommunityKeywordDTOList(keywordDTOList);
        }
        System.out.println(communityDTOList);
        return communityDTOList;
    }

    // 커뮤니티 번호로 부분 조회
    public Community findByCommunityCode(int communityNum) {
        Community community = communityRepository.findById(communityNum);

        return community;
    }
    @Transactional
    public int registCommunity(CommunityDTO communityDTO) throws IOException {
        Community community = new Community(communityDTO);
        List<CommunityKeywordDTO> keyword = communityDTO.getCommunityKeywordDTOList();
        List<CommunityKeyword> communityKeywordList = new ArrayList<>();

        for(int i = 0; i < keyword.size(); i++){
            System.out.println(keyword.get(i));
            Keyword findKeyword = keywordRepository.findById(keyword.get(i).getKeywordCode());
            communityKeywordList.add(new CommunityKeyword(new CommunityPK(community.getCommunityNum(), findKeyword.getKeywordCode()), community, findKeyword));
        }

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
        // 업데이트할 community 게시글이 있는지 확인하기
        if (findCommunity == null) {
            return 0;
        }

        // 매개 변수의 값으로 기존 커뮤니티의 필드를 업데이트하기
        findCommunity.setCommunityTitle(communityDTO.getCommunityTitle());
        findCommunity.setCommunityIntro(communityDTO.getCommunityIntro());
        findCommunity.setCategoryCode(communityDTO.getCategoryCode());
        findCommunity.setLocalCode(communityDTO.getLocalCode());
        findCommunity.setCommunityStatus(communityDTO.getCommunityStatus());
        findCommunity.setPostUpdateDate(new Date());

        // CommunityKeywordList 업데이트하기
        List<CommunityKeyword> updatedKeywords = new ArrayList<>();
        for (CommunityKeywordDTO keywordDTO : communityDTO.getCommunityKeywordDTOList()) {
            Keyword keyword = keywordRepository.findById(keywordDTO.getKeywordCode());
            if (keyword != null) {
                CommunityKeyword communityKeyword = new CommunityKeyword(new CommunityPK(findCommunity.getCommunityNum(), keyword.getKeywordCode()), findCommunity, keyword);
                updatedKeywords.add(communityKeyword);
            }
        }
        findCommunity.setCommunityKeywordList(updatedKeywords);

        // update된 community를 entity에 저장
        Community updateCommunity = communityRepository.save(findCommunity);

        if (updateCommunity != null) {
            return 1;
        } else {
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
    public List<CommunityDTO> CommunityListFilters(Integer categoryCode, Integer localCode) {

        List<CommunityDTO> communityList = new ArrayList<>();

        if (categoryCode != null && localCode != null){
            communityList = communityRepository.findByCategoryCodeAndLocalCode(categoryCode, localCode);
        } else if (categoryCode != null) {
            communityList = communityRepository.findByCategoryCode(categoryCode);
        } else if(localCode != null) {
            communityList = communityRepository.findByLocalCode(localCode);
        }

        return communityList;
    }
    // Integer -> null , int -> null x
}