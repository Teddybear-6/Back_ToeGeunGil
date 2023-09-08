package com.teddybear6.toegeungil.hobby.repository;

import com.teddybear6.toegeungil.hobby.entity.HobbyJoin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HobbyJoinRepository extends JpaRepository<HobbyJoin,Integer> {

    HobbyJoin findByHobbyCodeAndUserNo(int hobbyCode, int userNo);

    HobbyJoin findById(int joinNum);

    List<HobbyJoin> findAllByHobbyCode(int hobbyCode);
}
