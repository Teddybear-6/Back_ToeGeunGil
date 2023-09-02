package com.teddybear6.toegeungil.keyword.repository;

import com.teddybear6.toegeungil.keyword.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository  extends JpaRepository<Keyword,Integer> {

    List<Keyword> findBykeywordName(String keywordName);
}
