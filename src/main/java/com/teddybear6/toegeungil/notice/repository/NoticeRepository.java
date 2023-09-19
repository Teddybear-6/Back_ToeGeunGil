package com.teddybear6.toegeungil.notice.repository;

import com.teddybear6.toegeungil.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    Notice findById(int noticeNum);
    List<Notice> findAll();
}
