package com.teddybear6.toegeungil.social.service;

import com.teddybear6.toegeungil.social.dto.SocialDTO;
import com.teddybear6.toegeungil.social.entity.Social;
import com.teddybear6.toegeungil.social.repository.SocialRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class SocialService {

    private final SocialRepository socialRepository;

    public SocialService(SocialRepository socialRepository) {
        this.socialRepository = socialRepository;
    }

    public List<Social> readAllSocial() {
        //01_소셜 전체 조회(/social)
        List<Social> socialList = socialRepository.findAll();
        return socialList;
    }

    public Social readSocialPostNum(int socialNum) {
        //02_소셜 부분 조회(/social/{socialNum})
        Social social = socialRepository.findById(socialNum);
        return social;
    }

    /*
    @Transactional
    - 스프링 프레임워크는 @Transactional이 붙어 있는 클래스나 메소드에 틀랜잭션을 적용한다.
    - 외부에서 이 클래스의 해당 클래스를 호출할 때 트랜잭션을 시작학하고, 메소드를 종료할 때 트랜잭션을 커밋한다.
    - 만약 예외가 발생할 경우, 트랜잭션을 롤백한다. (JPA p.503) */

    @Transactional
    public int SocialPostRegistration(Social social) {
        //03_소셜 등록(/social)
        Social result = socialRepository.save(social);
        if (Objects.isNull(result)) {
            return 0; //result가 null일 경우 0 반환
        } else {
            return 1;
        }
    }

    @Transactional
    public int updateSocialPostNum(Social findSocial, SocialDTO social) {
        //04_소셜 수정(/social{socialNum})

        if (!Objects.isNull(social.getSocialName())) { //게시글 제목
            //넘어온 값이 null이 아닐 경우(값이 입력되었을 경우)
            findSocial.setSocialName(social.getSocialName());
        }
        if (!Objects.isNull(social.getSocialDate())) { //모임 일자
            findSocial.setSocialDate(social.getSocialDate());
        }
        if (social.getSocialFixedNum() > 0) { //모임 정원
            //넘어온 값이 0보다 클경우 (기본 값 0)
            findSocial.setSocialFixedNum(social.getSocialFixedNum());
        }
        if (!Objects.isNull(social.getSocialStartTime())) { //모임 시작 시간
            findSocial.setSocialStartTime(social.getSocialStartTime());
        }
        if (!Objects.isNull(social.getSocialEndTime())) { //모임 종료 시간
            findSocial.setSocialEndTime(social.getSocialEndTime());
        }
        if (social.getFileNum() > 0) { //사진 번호
            findSocial.setFileNum(social.getFileNum());
        }
        if (social.getCategoryCode() > 0) { //카테고리 번호
            findSocial.setCategoryCode(social.getCategoryCode());
        }
        if (social.getKeywordCode() > 0) { //키워드 번호
            findSocial.setKeywordCode(social.getKeywordCode());
        }
        if (social.getLocalCode() > 0) { //지역 번호
            findSocial.setLocalCode(social.getLocalCode());
        }
        if (!Objects.isNull(social.getLocalDetails())) { //지역 상세
            findSocial.setLocalDetails(social.getLocalDetails());
        }
        if (!Objects.isNull(social.getSocialIntro())) { //모임 소개
            findSocial.setSocialIntro(social.getSocialIntro());
        }
        if (!Objects.isNull(social.getSocialOther())) { //모임 기타 사항
            findSocial.setSocialOther(social.getSocialOther());
        }
        if (!Objects.isNull(social.getPostModiDate())) { //게시글 수정일
            findSocial.setPostModiDate(social.getPostModiDate());
        }
        if (!Objects.isNull(social.getSocialState())) { //게시글 상태
            findSocial.setSocialState(social.getSocialState());
        }

        Social result = socialRepository.save(findSocial);
        if (Objects.isNull(result)) {
            return 0;
        } else {
            return 1;
        }
    }
}
