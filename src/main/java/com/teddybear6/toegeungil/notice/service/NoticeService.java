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

    public Notice findNoticeByCode(int noticeNum) {
        Notice notice = noticeRegistory.findById(noticeNum);
        return notice;
    }

    /* 전체 조회 */
    public List<Notice> findAllNotice() {
        List<Notice> noticeList = noticeRegistory.findAll();
        return noticeList;
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
    @Transactional
    public int updateNotice(Notice findnotice, NoticeDetailDTO noticeDetailDTO) {
        findnotice.setNoticeTitle(noticeDetailDTO.getNoticeTitle());
        findnotice.setNoticeContent(noticeDetailDTO.getNoticeContent());

        Notice result = noticeRegistory.save(findnotice);

        if (Objects.isNull(result)) {
            return 0;
        } else {
            return 1;
        }
    }

    /* 삭제 */
    @Transactional
    public int deleteNotice(int noticeNum) {
        noticeRegistory.deleteById(noticeNum);

        Notice result = noticeRegistory.findById(noticeNum);
        System.out.println(result);
        if(Objects.isNull(result)){
            return 1;
        }else {
            return 0;
        }
    }
}
