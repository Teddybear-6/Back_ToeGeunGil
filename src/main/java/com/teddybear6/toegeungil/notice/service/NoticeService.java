package com.teddybear6.toegeungil.notice.service;

import com.teddybear6.toegeungil.notice.dto.NoticeDetailDTO;
import com.teddybear6.toegeungil.notice.entity.Notice;
import com.teddybear6.toegeungil.notice.repository.NoticeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRegistory) {
        this.noticeRepository = noticeRegistory;
    }

    public Notice findNoticeByCode(int noticeNum) {
        Notice notice = noticeRepository.findById(noticeNum);
        return notice;
    }

    /* 전체 조회 */
    public List<Notice> findAllNotice(final Pageable pageable) {
        List<Notice> noticeList = noticeRepository.findAllByOrderByNoticeNumDesc(pageable);
        return noticeList;
    }

    /* 등록 */
    @Transactional
    public int registNotice(Notice notice) {
        Notice result = noticeRepository.save(notice);
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

        Notice result = noticeRepository.save(findnotice);

        if (Objects.isNull(result)) {
            return 0;
        } else {
            return 1;
        }
    }

    /* 삭제 */
    @Transactional
    public int deleteNotice(int noticeNum) {
        noticeRepository.deleteById(noticeNum);

        Notice result = noticeRepository.findById(noticeNum);
        System.out.println(result);
        if(Objects.isNull(result)){
            return 1;
        }else {
            return 0;
        }
    }

    /* paging */
    public List<Notice> readAllNoticeSize() {
        List<Notice> noticeList=noticeRepository.findAll();
        return noticeList;
    }
}
