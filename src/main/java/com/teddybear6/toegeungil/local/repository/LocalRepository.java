package com.teddybear6.toegeungil.local.repository;

import com.teddybear6.toegeungil.local.entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalRepository extends JpaRepository<Local,Integer> {
    List<Local> findBylocalName(String localName);
}
