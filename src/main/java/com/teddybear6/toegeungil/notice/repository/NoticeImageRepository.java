package com.teddybear6.toegeungil.notice.repository;

import com.teddybear6.toegeungil.notice.entity.NoticeImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeImageRepository extends JpaRepository<NoticeImage, Integer> {
    NoticeImage findByNoticeNum(int noticeNum);
}
