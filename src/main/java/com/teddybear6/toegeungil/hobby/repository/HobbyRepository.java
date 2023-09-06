package com.teddybear6.toegeungil.hobby.repository;

import com.teddybear6.toegeungil.hobby.entity.Hobby;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.ContentHandler;
import java.util.List;

public interface HobbyRepository extends JpaRepository<Hobby,Integer> {

    Hobby findById(int hobbyCode);


    List<Hobby> findByCategoryCode(int categoryCode, Pageable pageable);
}
