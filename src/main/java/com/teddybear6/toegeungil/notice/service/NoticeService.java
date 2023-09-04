package com.teddybear6.toegeungil.notice.service;

import com.teddybear6.toegeungil.notice.dto.NoticeDetailDTO;
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

    public Notice findNoticeByCode(int noticeNum) {
        Notice notice = noticeRegistory.findById(noticeNum);
        return notice;
    }

    /* 등록 */
    @Transactional
    public int registNotice(Notice notice) {
        Notice result = noticeRegistory.save(notice);
        System.out.println(result);

        if (Objects.isNull(result)) {
            return 0;
        } else {
            return 1;
        }
    }


//    public int updateNotice(NoticeDetailDTO noticeDetailDTO) {
//        if (!Objects.isNull(noticeDetailDTO){
//
//        }
//    }

    //    public int updateNotice(Notice findnotice, Notice upnotice) {
//        if(!Objects.isNull(upnotice.NoticeDetailDto()))
//    }

    /* 삭제 */
    public Notice deleteNotice(int noticeNum) {
        noticeRegistory.deleteById(noticeNum);

        Notice notice = noticeRegistory.findById(noticeNum);
        System.out.println(notice);

        return notice;
    }
}
