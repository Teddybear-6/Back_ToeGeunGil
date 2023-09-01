package com.teddybear6.toegeungil.notice.service;

import com.teddybear6.toegeungil.notice.entity.Notice;
import com.teddybear6.toegeungil.notice.repository.NoticeRegistory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class NoticeService {

    private final NoticeRegistory noticeRegistory;

    public NoticeService(NoticeRegistory noticeRegistory) {
        this.noticeRegistory = noticeRegistory;
    }


    @Transactional
    public int registNotice(Notice notice) {
        Notice result = noticeRegistory.save(notice);
        System.out.println(result);

        if(Objects.isNull(result)){
            return 0;
        }else {
            return 1;
        }
    }
}
