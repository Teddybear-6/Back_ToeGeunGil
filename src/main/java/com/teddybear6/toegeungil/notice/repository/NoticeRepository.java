package com.teddybear6.toegeungil.notice.repository;

import com.teddybear6.toegeungil.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    Notice findById(int noticeNum);
}
