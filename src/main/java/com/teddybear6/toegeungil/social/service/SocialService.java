package com.teddybear6.toegeungil.social.service;

import com.teddybear6.toegeungil.category.entity.Category;
import com.teddybear6.toegeungil.category.repository.CategoryRepository;
import com.teddybear6.toegeungil.common.utils.ImageApi;
import com.teddybear6.toegeungil.keyword.entity.Keyword;
import com.teddybear6.toegeungil.keyword.repository.KeywordRepository;
import com.teddybear6.toegeungil.local.entity.Local;
import com.teddybear6.toegeungil.local.repository.LocalRepository;
import com.teddybear6.toegeungil.social.dto.SocialDTO;
import com.teddybear6.toegeungil.social.dto.SocialKeywordDTO;
import com.teddybear6.toegeungil.social.entity.*;
import com.teddybear6.toegeungil.social.repository.*;
import com.teddybear6.toegeungil.common.utils.ImageUtils;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SocialService {

    private final SocialRepository socialRepository; //소셜
    private final ImageRepository imageRepository; //파일
    private final ParticipateRepository participateRepository; //소셜참여
    private final CategoryRepository categoryRepository; //카테고리
    private final LocalRepository localRepository; //지역
    private final SocialKeywordRepository socialKeywordRepository;
    private final KeywordRepository keywordRepository;
    private final SocialImageRepository socialImageRepository;

    public SocialService(SocialRepository socialRepository, ImageRepository imageRepository, ParticipateRepository participateRepository, CategoryRepository categoryRepository, LocalRepository localRepository, SocialKeywordRepository socialKeywordRepository, KeywordRepository keywordRepository, SocialImageRepository socialImageRepository) {
        this.socialRepository = socialRepository;
        this.imageRepository = imageRepository;
        this.participateRepository = participateRepository;
        this.categoryRepository = categoryRepository;
        this.localRepository = localRepository;
        this.socialKeywordRepository = socialKeywordRepository;
        this.keywordRepository = keywordRepository;
        this.socialImageRepository = socialImageRepository;
    }


    public List<SocialDTO> readAllSocial() {
        //01_소셜 전체 조회(/social)
        List<Social> socialList = socialRepository.findAll(Sort.by(Sort.Direction.DESC, "SocialNum")); //socialNum 기준 내림차순
        List<SocialDTO> socialDTOList = socialList.stream().map(m -> new SocialDTO(m)).collect(Collectors.toList());

        for (int i = 0; i < socialList.size(); i++) {
            List<Keyword> keywordList = new ArrayList<>();
            List<SocialKeywordDTO> keywordDTOList = new ArrayList<>();
            for (int j = 0; j < socialList.get(i).getSocialKeywordList().size(); j++) {
                keywordList.add(socialList.get(i).getSocialKeywordList().get(j).getKeyword());
                keywordDTOList = keywordList.stream().map(m-> new SocialKeywordDTO(m)).collect(Collectors.toList());
            }
            socialDTOList.get(i).setKeywordDTOList(keywordDTOList);
        }
        System.out.println(socialDTOList);
        return socialDTOList;
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
    public int SocialPostRegistration(SocialDTO socialDTO, MultipartFile file) throws IOException, ParseException {
        //03_소셜 등록(/social)
        Social social = new Social(socialDTO);
        List<SocialKeywordDTO> keyword = socialDTO.getKeywordDTOList();
        List<SocialKeyword> keywordList = new ArrayList<>(); //null

        for (int i = 0; i < keyword.size(); i++) {
            //keywordRepository의 findById 사용하기
            //엔티티에서 keywordNum 컬럼 삭제하기
            Keyword findKeyword = keywordRepository.findById(keyword.get(i).getKeywordCode());
            keywordList.add(new SocialKeyword(new SocialKeywordPK(social.getSocialNum(), findKeyword.getKeywordCode()), social, findKeyword));
        }

        social.setSocialKeywordList(keywordList);
        Social findSocial = socialRepository.save(social);

        //이미지 로직
        ResponseEntity res = ImageApi.singleImage(file);
        System.out.println(res.getBody().toString());
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)  parser.parse(res.getBody().toString());
        System.out.println(jsonObject);
        JSONObject fileInfo =  (JSONObject) jsonObject.get("fileInfo");

        SocialImage image = new SocialImage();
        image.setSocialNum(findSocial.getSocialNum());

        String originalname =  (String)fileInfo.get("originalname");
        String path =  ((String)fileInfo.get("path")).replace("uploads\\","");

        image.setName(originalname);
        image.setPath(path);

        SocialImage findImage = socialImageRepository.save(image);
        System.out.println(image);
        System.out.println(findImage);

        if (Objects.isNull(findSocial)) {
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
//        if (social.getFileNum() > 0) { //사진 번호
//            findSocial.setFileNum(social.getFileNum());
//        }
        if (social.getCategoryCode() > 0) { //카테고리 번호
            findSocial.setCategoryCode(social.getCategoryCode());
        }
//        if (social.getKeywordCode() > 0) { //키워드 번호
//            findSocial.setKeywordCode(social.getKeywordCode());
//        }
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
            findSocial.setPostModiDate(new Date());
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

    public byte[] downloadSocialImgeId(Long imageId) {
        //사진 번호로 이미지 다운로드!!
        Image imageData = imageRepository.findByImageId(imageId)
                .orElseThrow(RuntimeException::new);
        return ImageUtils.decompressImage(imageData.getImageData());
    }


    /*
    참여하기*/
    public List<Participate> readSocialParticipateUser(int socialNum) {
        //20_소셜 참여 회원 조회(/participate/{게시글 번호})
        List<Participate> participateList = participateRepository.findAllBySocialNum(socialNum);

        return participateList;
    }

    public Participate findSocialParticipateRegistration(int socialNum, int userNum) {
        //21_소셜 참여(/participate) -> 게시글번호 AND 회원번호의 정보가 있는지 확인하기. (참여하기 되어있는지 확인)
        Participate participate = participateRepository.findBySocialNumAndUserNum(socialNum, userNum);

        return participate;
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

    @Transactional
    public int SocialParticipateDelete(Participate findParticipate) {
        //21_소셜 참여가 이미 되어있는 경우, 모임 참여 삭제
        participateRepository.delete(findParticipate);

        Participate participate = participateRepository.findBySocialNumAndUserNum(findParticipate.getSocialNum(),findParticipate.getUserNum());
        if(Objects.isNull(participate)){
            return 1;
        }else {
            return 0;
        }
    }


    /*
    필터*/
    public Category readSocialPostCategory(int categoryCode) {
        //카테고리 코드 조회
        Category category = categoryRepository.findById(categoryCode);
        return category;
    }

    public Local readSocialPostLocal(int localCode) {
        //지역 코드 조회
        Local local = localRepository.findById(localCode);
        return local;
    }

    public List<Social> readSocialPostWhereCategoryCode(int categoryCode) {
        //30_카테고리 코드 필터 (받아온 카테고리 코드로 소셜 게시글 리스트로 조회)
        List<Social> social = socialRepository.findByCategoryCode(categoryCode);

        return social;
    }

    public List<Social> readSocialPostWhereLocalCode(int localCode) {
        //31_지역 코드 필터 (받아온 지역 코드로 소셜 게시글 리스트로 조회)
        List<Social> socialList = socialRepository.findByLocalCode(localCode);
        return socialList;
    }

    public List<Social> readSocialFilterCategoryAndLocal(Category category, Local local) {
        //32_카테고리 AND 지역 필터
        List<Social> socialList = socialRepository.findByCategoryCodeAndLocalCode(category.getCategoryCode(), local.getLocalCode());
        System.out.println("service : " + socialList);

        return socialList;
    }

//    @Transactional //이미지 업로드 수정 2023.09.18 (소셜 게시글 등록과 합침)
//    public int uploadImage(MultipartFile file) throws IOException, ParseException {
//        ResponseEntity res = imageApi.singleImage(file);
//        System.out.println(res.getBody().toString());
//        JSONParser parser = new JSONParser();
//        JSONObject jsonObject = (JSONObject)  parser.parse(res.getBody().toString());
//        System.out.println(jsonObject);
//        JSONObject fileInfo =  (JSONObject) jsonObject.get("fileInfo");
//
//        SocialImage image = new SocialImage();
////        image.setSocialNum(findSocial.getSocialNum());
//
//        String originalname =  (String)fileInfo.get("originalname");
//        String path =  ((String)fileInfo.get("path")).replace("uploads\\","");
//
//        image.setName(originalname);
//        image.setPath(path);
//
//        SocialImage findImage = socialImageRepository.save(image);
//        System.out.println(image);
//        System.out.println(findImage);
//
//        if (Objects.isNull(findImage)) {
//            return 0; //result가 null일 경우 0 반환
//        } else {
//            return 1;
//        }
//
//    }

    public SocialImage downloadImage(int socialNum) {
        SocialImage socialImage = socialImageRepository.findBySocialNum(socialNum);
        return socialImage;
    }

}
