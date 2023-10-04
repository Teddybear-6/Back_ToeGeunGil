package com.teddybear6.toegeungil.notice.controller;

import com.teddybear6.toegeungil.auth.dto.AuthUserDetail;
import com.teddybear6.toegeungil.notice.dto.NoticeDetailDTO;
import com.teddybear6.toegeungil.notice.entity.Notice;
import com.teddybear6.toegeungil.notice.service.NoticeService;
import com.teddybear6.toegeungil.user.entity.UserEntity;
import com.teddybear6.toegeungil.user.sevice.UserViewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/notices") // 도메인을 의미
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "공지사항 Api", tags = {"04. Notice Info"}, description = "공지사항 Api")
@ApiResponses({
        @ApiResponse(code = 200,message = "성공"),
        @ApiResponse(code = 404,message = "잘못된 접근") ,
        @ApiResponse(code = 500,message = "서버에러")
})
public class NoticeController {

    private final NoticeService noticeService;
    private final UserViewService userViewService;

    public NoticeController(NoticeService noticeService, UserViewService userViewService) {
        this.noticeService = noticeService;
        this.userViewService = userViewService;
    }

    /* <GET> /notices : 공지사항 목록 전체 조회 */
    @GetMapping
    @ApiOperation(value = "공지사항 전체 조회 Api", notes = "공지사항 전체 목록을 조회한다.")
    public ResponseEntity<List<?>> findAllNotice(final Pageable pageable) {
        List<Notice> noticeList = noticeService.findAllNotice(pageable);

        if (noticeList.size() <= 0) {
            List<String> error = new ArrayList<>();
            error.add("String");
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok().body(noticeList);
    }

    /* <GET> /notices/{noticeNum} : 공지사항 목록 상세 조회 */
    @GetMapping("/{noticeNum}")
    @ApiOperation(value = "공지사항 단일 조회 Api", notes = "공지사항 게시글 번호로 해당 게시글을 조회한다.")
    public ResponseEntity<Object> findNoticeByCode(@PathVariable int noticeNum) {
        Notice notice = noticeService.findNoticeByCode(noticeNum);

        /* 공지번호가 null인 경우 */
        if (Objects.isNull(noticeNum)) {
            return ResponseEntity.status(404).body(new String("공지번호가 존재하지 않습니다"));
        }
        return ResponseEntity.ok().body(notice);
    }

    /* <POST> /notices: 공지사항 등록 */
    @PostMapping
    @ApiOperation(value = "공지사항 작성 Api", notes = "공지사항 게시글을 작성한다.")
    public ResponseEntity<?> registNotice(@RequestBody Notice notice) {
        System.out.println(notice);
        notice.setNoticeDate(new Date());
        int result = noticeService.registNotice(notice);

        if (result > 0) {
            return ResponseEntity.ok().body("공지사항 등록 성공입니다");
        } else {
            return ResponseEntity.status(500).body("공지사항 등록 실패입니다");
        }
    }


    /* <PUT> /notices/{noticeNum} : 공지사항 수정 */
    @PutMapping("/{noticeNum}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "공지사항 수정 Api", notes = "공지사항 게시글 번호로 해당 게시글을 수정한다.")
    public ResponseEntity<?> updateNotice(@AuthenticationPrincipal AuthUserDetail userDetails, @PathVariable int noticeNum, @RequestBody NoticeDetailDTO noticeDetailDTO) {
        System.out.println(userDetails);
        UserEntity userEntity = userViewService.findUserEmail(userDetails.getUserEntity().getUserEmail());
        Map<String, String> respose = new HashMap<>();

        if (Objects.isNull(userEntity)) {
            respose.put("value", "관리자가 아닙니다.");
            return ResponseEntity.status(500).body(respose);
        }

        Notice findnotice = noticeService.findNoticeByCode(noticeNum);

        /* 조회 성공 시 엔티티 존재, 실패할 시 업데이트 할 대상 존재하지 않음 */
        if (Objects.isNull(findnotice)) {
            return ResponseEntity.status(404).body("공지사항이 존재하지 않습니다");
        }

        int result = noticeService.updateNotice(findnotice, noticeDetailDTO);

        /* 업데이트 성공,실패 여부 */
        if (result > 0) {
            return ResponseEntity.ok().body("공지사항 수정 성공입니다");
        } else {
            return ResponseEntity.status(400).body("공지사항 수정 실패입니다");
        }

    }

    /* <DELETE> /notices/{noticeNum} : 공지사항 삭제 */
    @DeleteMapping("/{noticeNum}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "공지사항 삭제 Api", notes = "공지사항 게시글 번호로 해당 게시글을 삭제한다.")
    public ResponseEntity<?> deleteNotice(@AuthenticationPrincipal AuthUserDetail userDetails, @PathVariable int noticeNum) {
        UserEntity userEntity = userViewService.findUserEmail(userDetails.getUserEntity().getUserEmail());
        Map<String, String> respose = new HashMap<>();

        if (Objects.isNull(userEntity)) {
            respose.put("value", "관리자가 아닙니다.");
            return ResponseEntity.status(500).body(respose);
        }

        Notice notice = noticeService.findNoticeByCode(noticeNum);

        if (Objects.isNull(notice)) {
            return ResponseEntity.status(404).body("공지사항이 존재하지 않습니다");
        }

        int result = noticeService.deleteNotice(noticeNum);

        if (result > 0) {
            return ResponseEntity.ok().body("공지사항 삭제 성공입니다");
        } else {
            return ResponseEntity.status(400).body("공지사항 삭제 실패입니다");
        }
    }

    /* paging */
    @GetMapping("/size")
    @ApiOperation(value = "공지사항 전체 사이즈 조회 Api", notes = "공지사항 전체 목록의 사이즈를 조회한다.")
    public ResponseEntity<?> noticeSize() {
        List<Notice> noticeList = noticeService.readAllNoticeSize();
        return ResponseEntity.ok().body(noticeList.size());
    }

    @GetMapping("/img/{noticeNum}")
    @ApiOperation(value = "공지사항 이미지 다운로드 Api", notes = "공지사항 게시글 번호로 해당 게시글의 이미지를 조회한다.")
    public ResponseEntity<?> downloadImage(@PathVariable int noticeNum){
        NoticeImage noticeImage = NoticeService.downloadImage(noticeNum);

        if(Objects.isNull(noticeImage)){
            return ResponseEntity.status(404).body("이미지 조회에 실패하였습니다");
        }else {
            NoticeImageDTO noticeImageDTO = new NoticeImageDTO(noticeImage);
            return ResponseEntity.ok().body(noticeImageDTO);
        }
    }
}
