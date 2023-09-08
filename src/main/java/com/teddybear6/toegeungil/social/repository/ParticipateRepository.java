package com.teddybear6.toegeungil.social.repository;

import com.teddybear6.toegeungil.social.entity.Participate;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ParticipateRepository extends JpaRepository<Participate, Integer> {
    List<Participate> findAllBySocialNum(int socialNum); //20_소셜 참여 회원 조회
    Participate findBySocialNumAndUserNum(int socialNum, int userNum); //21_소셜 참여 조회 (참여가 이미 되어있는 회원인가?)

}
