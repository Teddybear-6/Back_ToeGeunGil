package com.teddybear6.toegeungil.keyword.service;

import com.teddybear6.toegeungil.keyword.entity.Keyword;
import com.teddybear6.toegeungil.keyword.repository.KeywordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

    public List<Keyword> findBykeywordName(String keywordName) {
        List<Keyword> keywordList = keywordRepository.findBykeywordName(keywordName);
        return keywordList;
    }

    @Transactional
    public int registKeyword(Keyword keyword) {
        Keyword findKeyword = keywordRepository.save(keyword);

        if(Objects.isNull(findKeyword)){
            return 0;
        }else {
            return 1;
        }
    }

    public Keyword findById(int keywordCode) {
        Keyword keyword = keywordRepository.findById(keywordCode);
        return keyword;
    }
}
