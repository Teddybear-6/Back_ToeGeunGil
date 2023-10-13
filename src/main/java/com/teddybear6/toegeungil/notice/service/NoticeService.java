package com.teddybear6.toegeungil.notice.service;

import com.teddybear6.toegeungil.common.utils.ImageApi;
import com.teddybear6.toegeungil.notice.dto.NoticeDetailDTO;
import com.teddybear6.toegeungil.notice.entity.Notice;
import com.teddybear6.toegeungil.notice.entity.NoticeImage;
import com.teddybear6.toegeungil.notice.repository.NoticeImageRepository;
import com.teddybear6.toegeungil.notice.repository.NoticeRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeImageRepository noticeImageRepository;

    public NoticeService(NoticeRepository noticeRepository, NoticeImageRepository noticeImageRepository) {
        this.noticeRepository = noticeRepository;
        this.noticeImageRepository = noticeImageRepository;
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
    public int registNotice(Notice notice, MultipartFile file)  throws IOException, ParseException {
        Notice result = noticeRepository.save(notice);
        System.out.println(result);

        //이미지 로직
        ResponseEntity res = ImageApi.singleImage(file);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(res.getBody().toString());
        JSONObject fileInfo = (JSONObject) jsonObject.get("fileInfo");

        NoticeImage image = new NoticeImage();
        image.setNoticeNum(result.getNoticeNum());

        String originalname = (String) fileInfo.get("originalname");
        String path = ((String) fileInfo.get("path")).replace("uploads\\", "");

        image.setName(originalname);
        image.setPath(path);

        NoticeImage findImage = noticeImageRepository.save(image);

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

        findnotice.setNoticeDate(new Date());

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

    public NoticeImage downloadImage(int noticeNum) {
        NoticeImage noticeImage = noticeImageRepository.findByNoticeNum(noticeNum);
        return noticeImage;
    }

}
