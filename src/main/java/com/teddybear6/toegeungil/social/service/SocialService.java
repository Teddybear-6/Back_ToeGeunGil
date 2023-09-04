package com.teddybear6.toegeungil.social.service;

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
}
