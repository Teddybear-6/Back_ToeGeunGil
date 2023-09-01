package com.teddybear6.toegeungil.notice.controller;

import com.teddybear6.toegeungil.notice.entity.Notice;
import com.teddybear6.toegeungil.notice.service.NoticeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notices") // 도메인을 의미
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    /* <GET> /notices : 공지사항 목록 조회 */

    /* <GET> /notices/{noticeID} : 공지사항 목록 상세 조회 */

    /* <POST> /notices: 공지사항 등록 */
    @PostMapping
    public ResponseEntity<?> regist(Notice notice){
        int result = noticeService.registNotice(notice);

        return ResponseEntity.ok().body("공지사항 등록 성공");
    }


    /* <PUT> /notices/{noticeID} : 공지사항 수정 */

    /* <DELETE> /notices/{noticeID} : 공지사항 삭제 */
}
