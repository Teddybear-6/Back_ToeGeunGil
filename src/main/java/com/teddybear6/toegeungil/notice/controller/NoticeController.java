package com.teddybear6.toegeungil.notice.controller;

import com.teddybear6.toegeungil.notice.dto.NoticeDetailDTO;
import com.teddybear6.toegeungil.notice.entity.Notice;
import com.teddybear6.toegeungil.notice.service.NoticeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/notices") // 도메인을 의미
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    /* <GET> /notices : 공지사항 목록 조회 */

    /* <GET> /notices/{noticeNum} : 공지사항 목록 상세 조회 */
    @GetMapping("/{noticeNum}")
    public ResponseEntity<Object> findNoticeByCode(@PathVariable int noticeNum) {
        Notice notice = noticeService.findNoticeByCode(noticeNum);

        /* 공지번호가 null인 경우 */
        if (Objects.isNull(noticeNum)) {
            return ResponseEntity.status(404).body(new String("공지번호가 존재하지 않습니다"));
        }
        return ResponseEntity.ok().body(noticeNum);
    }

    /* <POST> /notices: 공지사항 등록 */
    @PostMapping
    public ResponseEntity<?> registNotice(Notice notice) {
        int result = noticeService.registNotice(notice);

        return ResponseEntity.ok().body("공지사항 등록 성공");
    }


    /* <PUT> /notices/{noticeID} : 공지사항 수정 */
//    @PutMapping("/{noticeNum}")
//    public ResponseEntity<?> updateNotice(Notice notice, NoticeDetailDTO noticeDetailDTO) {
//        Notice findnotice = noticeService.findNoticeByCode(notice.getNoticeNum());
//
//        /* 조회 성공 시 엔티티 존재, 실패할 시 업데이트 할 대상 존재하지 않음 */
//        if (Objects.isNull(findnotice)) {
//            return ResponseEntity.ok().body("공지사항이 존재하지 않습니다");
//        }
//
//        int result = noticeService.updateNotice(noticeDetailDTO);
//
//        /* 업데이트 성공,실패 여부 */
//        if(result >0){
//            return ResponseEntity.ok().body("공지사항이 수정 성공입니다");
//        }else {
//            return ResponseEntity.status(400).body("공지사항 수정 실패입니다");
//        }
//
//    }

    /* <DELETE> /notices/{noticeID} : 공지사항 삭제 */
}
