package com.teddybear6.toegeungil.social.service;

import com.teddybear6.toegeungil.social.dto.SocialDTO;
import com.teddybear6.toegeungil.social.entity.Image;
import com.teddybear6.toegeungil.social.entity.Participate;
import com.teddybear6.toegeungil.social.entity.Social;
import com.teddybear6.toegeungil.social.repository.ImageRepository;
import com.teddybear6.toegeungil.social.repository.ParticipateRepository;
import com.teddybear6.toegeungil.social.repository.SocialRepository;
import com.teddybear6.toegeungil.common.utils.ImageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Objects;

@Service
public class SocialService {

    private final SocialRepository socialRepository; //소셜
    private final ImageRepository imageRepository; //파일
    private final ParticipateRepository participateRepository; //소셜참여

    public SocialService(SocialRepository socialRepository, ImageRepository imageRepository, ParticipateRepository participateRepository) {
        this.socialRepository = socialRepository;
        this.imageRepository = imageRepository;
        this.participateRepository = participateRepository;
    }

    public List<Social> readAllSocial() {
        //01_소셜 전체 조회(/social)
        List<Social> socialList = socialRepository.findAll();
        return socialList;
    }

    public Social readSocialPostNum(int socialNum) {
        //02_소셜 부분 조회(/social/{socialNum})
        Social social = socialRepository.findById(socialNum);
        return social;
    }

    /*
    @Transactional
    - 스프링 프레임워크는 @Transactional이 붙어 있는 클래스나 메소드에 틀랜잭션을 적용한다.
    - 외부에서 이 클래스의 해당 클래스를 호출할 때 트랜잭션을 시작학하고, 메소드를 종료할 때 트랜잭션을 커밋한다.
    - 만약 예외가 발생할 경우, 트랜잭션을 롤백한다. (JPA p.503) */

    @Transactional
    public int SocialPostRegistration(Social social) {
        //03_소셜 등록(/social)
        Social result = socialRepository.save(social);
        if (Objects.isNull(result)) {
            return 0; //result가 null일 경우 0 반환
        } else {
            return 1;
        }
    }

    @Transactional
    public int updateSocialPostNum(Social findSocial, SocialDTO social) {
        //04_소셜 수정(/social{socialNum})

        if (!Objects.isNull(social.getSocialName())) { //게시글 제목
            //넘어온 값이 null이 아닐 경우(값이 입력되었을 경우)
            findSocial.setSocialName(social.getSocialName());
        }
        if (!Objects.isNull(social.getSocialDate())) { //모임 일자
            findSocial.setSocialDate(social.getSocialDate());
        }
        if (social.getSocialFixedNum() > 0) { //모임 정원
            //넘어온 값이 0보다 클경우 (기본 값 0)
            findSocial.setSocialFixedNum(social.getSocialFixedNum());
        }
        if (!Objects.isNull(social.getSocialStartTime())) { //모임 시작 시간
            findSocial.setSocialStartTime(social.getSocialStartTime());
        }
        if (!Objects.isNull(social.getSocialEndTime())) { //모임 종료 시간
            findSocial.setSocialEndTime(social.getSocialEndTime());
        }
        if (social.getFileNum() > 0) { //사진 번호
            findSocial.setFileNum(social.getFileNum());
        }
        if (social.getCategoryCode() > 0) { //카테고리 번호
            findSocial.setCategoryCode(social.getCategoryCode());
        }
        if (social.getKeywordCode() > 0) { //키워드 번호
            findSocial.setKeywordCode(social.getKeywordCode());
        }
        if (social.getLocalCode() > 0) { //지역 번호
            findSocial.setLocalCode(social.getLocalCode());
        }
        if (!Objects.isNull(social.getLocalDetails())) { //지역 상세
            findSocial.setLocalDetails(social.getLocalDetails());
        }
        if (!Objects.isNull(social.getSocialIntro())) { //모임 소개
            findSocial.setSocialIntro(social.getSocialIntro());
        }
        if (!Objects.isNull(social.getSocialOther())) { //모임 기타 사항
            findSocial.setSocialOther(social.getSocialOther());
        }
        if (!Objects.isNull(social.getPostModiDate())) { //게시글 수정일
            findSocial.setPostModiDate(social.getPostModiDate());
        }
        if (!Objects.isNull(social.getSocialState())) { //게시글 상태
            findSocial.setSocialState(social.getSocialState());
        }

        Social result = socialRepository.save(findSocial);
        if (Objects.isNull(result)) {
            return 0;
        } else {
            return 1;
        }
    }

    /*
    사진*/
    @Transactional
    public String uploadSocialImage(MultipartFile image) throws IOException {
        //10_사진 업로드
        //image_name을 새로 부여하기 위한 현재 시간 가져오기
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        int millis = now.get(ChronoField.MILLI_OF_SECOND);
        //새로 부여하는 image_name
        String newFileName = "image" + year + month + day + hour + minute + second + millis;

        Image imageData = imageRepository.save(
                new Image()
                        .imageName(newFileName/*시간으로 저장*/)
                        .imageOriName(image.getOriginalFilename())
                        .imageType(image.getContentType())
                        .imageData(ImageUtils.compressImage(image.getBytes()))
                        .builder()
        );

        if (imageData != null) {
            return "uploadSocialImage : " + image.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadSocialImage(String imageName) {
        //11_사진 다운로드(보여주기)
        Image imageData = imageRepository.findByImageName(imageName)
                .orElseThrow(RuntimeException::new);
        return ImageUtils.decompressImage(imageData.getImageData());
    }

    @Transactional
    public int SocialParticipateRegistration(Participate participate) {
        //21_소셜 참여(/participate)
        Participate result = participateRepository.save(participate);

        System.out.println(result);
        if (Objects.isNull(result)) {
            return 0; //result가 null일 경우 0 반환
        } else {
            return 1;
        }
    }

    public Participate findSocialParticipateRegistration(int socialNum, int userNum) {
        //21_소셜 참여(/participate) -> 게시글번호 AND 회원번호의 정보가 있는지 확인하기. (참여하기 되어있는지 확인)
        System.out.println("안녕 나 느는느느느느느느느 " + socialNum + ", " + userNum);
        Participate participate = participateRepository.findBySocialNumAndUserNum(socialNum, userNum);

        System.out.println("ser : " + participate);
        return participate;
    }

}
