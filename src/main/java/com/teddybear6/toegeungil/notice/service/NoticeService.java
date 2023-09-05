package com.teddybear6.toegeungil.notice.service;

import com.teddybear6.toegeungil.notice.dto.NoticeDetailDTO;
import com.teddybear6.toegeungil.notice.entity.Notice;
import com.teddybear6.toegeungil.notice.repository.NoticeRegistory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class NoticeService {

    private final NoticeRegistory noticeRegistory;

    public NoticeService(NoticeRegistory noticeRegistory) {
        this.noticeRegistory = noticeRegistory;
    }

    /* 전체 조회 */
    public List<Notice> findAllNotice() {
        List<Notice> noticeList = noticeRegistory.findAll();
        return noticeList;
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

    /* 수정 */
    public int updateNotice(Notice findNotice, String noticeTitle, String noticeContent) {
        findNotice.setNoticeTitle(noticeTitle);
        findNotice.setNoticeContent(noticeContent);
        findNotice.setNoticeModiDate(new Date());

        Notice result = noticeRegistory.save(findNotice);

        if(Objects.isNull(result)){
            return 0;
        }else {
            return 1;
        }
    }


    /* 삭제 */
    public Notice deleteNotice(int noticeNum) {
        noticeRegistory.deleteById(noticeNum);

        Notice notice = noticeRegistory.findById(noticeNum);
        System.out.println(notice);

        return notice;
    }
}
