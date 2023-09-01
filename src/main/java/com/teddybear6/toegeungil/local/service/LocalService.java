package com.teddybear6.toegeungil.local.service;

import com.teddybear6.toegeungil.local.entity.Local;
import com.teddybear6.toegeungil.local.repository.LocalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class LocalService {
    private final LocalRepository localRepository;

    public LocalService(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Transactional
    public int registLocal(Local local) {
        Local findLocal = localRepository.save(local);

        if (Objects.isNull(findLocal)) {
            return 0;
        } else {
            return 1;
        }
    }

    public List<Local> findAll() {
        List<Local> localList = localRepository.findAll();

        return localList;
    }

    public List<Local> findByName(String localName) {
        List<Local> local = localRepository.findBylocalName(localName);
        return local;
    }

    public Local findById(int localCode) {

        Local local = localRepository.findById(localCode);
        return local;
    }
}
