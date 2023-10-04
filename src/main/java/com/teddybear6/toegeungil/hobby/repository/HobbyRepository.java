package com.teddybear6.toegeungil.hobby.repository;

import com.teddybear6.toegeungil.hobby.dto.HobbyReviewDTO;
import com.teddybear6.toegeungil.hobby.entity.Hobby;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.ContentHandler;
import java.util.List;

public interface HobbyRepository extends JpaRepository<Hobby,Integer> {

    Hobby findById(int hobbyCode);


    Page<Hobby> findByCategoryCode(int categoryCode, Pageable pageable);

    Page<Hobby> findByLocalCode(int localCode, Pageable pageable);

    Page<Hobby> findByCategoryCodeAndLocalCode(int categoryCode, int localCode, Pageable pageable);

    List<Hobby> findByTutorCode(int userNo, Pageable pageable);

    List<Hobby> findByTutorCode(int tutorCode);





    Page<Hobby> findAllByOrderByHobbyCodeDesc(Pageable pageable);


    Page<Hobby> findByTutorCodeOrderByHobbyCodeDesc(int userNo, Pageable pageable);




    List<Hobby> findByHobbyTitleContaining(String name);

    Page<Hobby> findHobbyByHobbyTitleContaining(Pageable pageable, String name);

    List<Hobby> findByCategoryCode(int categoryCode);

    List<Hobby> findByLocalCode(int localCode);

    List<Hobby> findByCategoryCodeAndLocalCode(int categoryCode, int localCode);


}
