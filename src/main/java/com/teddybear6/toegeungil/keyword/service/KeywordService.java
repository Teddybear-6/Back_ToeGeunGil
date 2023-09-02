package com.teddybear6.toegeungil.keyword.service;

import com.teddybear6.toegeungil.keyword.entity.Keyword;
import com.teddybear6.toegeungil.keyword.repository.KeywordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordService {

    private final KeywordRepository keywordRepository ;

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public List<Keyword> findAll() {
        List<Keyword> keywordList = keywordRepository.findAll();

        return keywordList;
    }

    public List<Keyword> findByName(String keywordName) {
        List<Keyword> keywordList = keywordRepository.findBykeywordName(keywordName);
        return keywordList;
    }
}
