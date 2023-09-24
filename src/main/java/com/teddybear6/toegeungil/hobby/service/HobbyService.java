package com.teddybear6.toegeungil.hobby.service;

import com.teddybear6.toegeungil.common.utils.ImageApi;
import com.teddybear6.toegeungil.hobby.dto.*;
import com.teddybear6.toegeungil.hobby.entity.*;
import com.teddybear6.toegeungil.hobby.repository.*;
import com.teddybear6.toegeungil.common.utils.ImageUtils;
import com.teddybear6.toegeungil.keyword.entity.Keyword;
import com.teddybear6.toegeungil.keyword.repository.KeywordRepository;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class HobbyService {

    private final StorageRepository storageRepository;
    private final KeywordRepository keywordRepository;
    private final HobbyRepository hobbyRepository;

    private final HobbyKeywordRepository hobbyKeywordRepository;

    private final ReviewAnswerRepository reviewAnswerRepository;

    private final HobbyJoinRepository hobbyJoinRepository;

    private final HobbyReviewRepository hobbyReviewRepository;


    public HobbyService(StorageRepository storageRepository, KeywordRepository keywordRepository, HobbyRepository hobbyRepository, HobbyKeywordRepository hobbyKeywordRepository, ReviewAnswerRepository reviewAnswerRepository, HobbyJoinRepository hobbyJoinRepository, HobbyReviewRepository hobbyReviewRepository) {
        this.storageRepository = storageRepository;
        this.keywordRepository = keywordRepository;
        this.hobbyRepository = hobbyRepository;
        this.hobbyKeywordRepository = hobbyKeywordRepository;
        this.reviewAnswerRepository = reviewAnswerRepository;
        this.hobbyJoinRepository = hobbyJoinRepository;
        this.hobbyReviewRepository = hobbyReviewRepository;
    }

    @Transactional
    public int registHobby(HobbyDTO hobbyDTO, MultipartFile[] files) throws IOException, ParseException {
        Hobby hobby = new Hobby(hobbyDTO);
        List<HobbyKeywordDTO> keyword = hobbyDTO.getKeywordDTOList();
        List<HobbyKeyword> hobbyKeywordList = new ArrayList<>();
        List<HobbyImage> hobbyImages = new ArrayList<>();

        for (int i = 0; i < keyword.size(); i++) {
            Keyword findKeyword = keywordRepository.findById(keyword.get(i).getKeywordCode());
            hobbyKeywordList.add(new HobbyKeyword(new HobbyPk(hobby.getHobbyCode(), findKeyword.getKeywordCode()), hobby, findKeyword));
        }


        Hobby findHobby = hobbyRepository.save(hobby);
        hobbyKeywordRepository.saveAll(hobbyKeywordList);
        ResponseEntity res = ImageApi.multiImages(files);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(res.getBody().toString());
        JSONArray jsonArray1 = (JSONArray) jsonObject.get("fileInfo");


        for (int i = 0; i < jsonArray1.size(); i++) {
            HobbyImage image = new HobbyImage();
            image.setHobbyCode(findHobby.getHobbyCode());
            JSONObject obj = (JSONObject) jsonArray1.get(i);

            image.setName(((String) obj.get("originalname")));
            image.setPath(((String) obj.get("path")).replace("uploads\\", ""));
            hobbyImages.add(image);
        }
        List<HobbyImage> findImages = storageRepository.saveAll(hobbyImages);

        if (Objects.isNull(findHobby)) {
            return 0;
        } else {
            return 1;
        }


    }

    public List<HobbyGetDTO> findAll(final Pageable pageable) {

        List<Hobby> hobbyList = hobbyRepository.findAllByOrderByHobbyCodeDesc(pageable);
        List<HobbyGetDTO> hobbyGetDTOS = hobbyList.stream().map(m -> new HobbyGetDTO(m)).collect(Collectors.toList());

        for (int i = 0; i < hobbyList.size(); i++) {
            List<Keyword> keyword = new ArrayList<>();
            List<HobbyKeywordDTO> keywordDTOList = new ArrayList<>();
            for (int j = 0; j < hobbyList.get(i).getHobbyKeywordList().size(); j++) {
                keyword.add(hobbyList.get(i).getHobbyKeywordList().get(j).getKeyword());
                keywordDTOList = keyword.stream().map(m -> new HobbyKeywordDTO(m)).collect(Collectors.toList());
            }
            hobbyGetDTOS.get(i).setKeyword(keywordDTOList);
        }

        return hobbyGetDTOS;
    }

    public List<HobbyImage> findMainImage(int hobbyCode) {
        List<HobbyImage> hobbyImage = storageRepository.findByhobbyCode(hobbyCode);

        return hobbyImage;
    }

    public Hobby findById(int hobbyCode) {
        Hobby hobby = hobbyRepository.findById(hobbyCode);

        return hobby;


    }

    public HobbyImage detailImage(int hobbyCode) {
        HobbyImage hobbyImages = storageRepository.findById(hobbyCode);

        return hobbyImages;
    }

    public int deleteById(Hobby hobby) {
        hobby.setHobbyStatus("N");
        hobbyRepository.save(hobby);

        if (hobby.getHobbyStatus().equals("N")) {
            return 1;
        } else {
            return 0;
        }

    }

    @Transactional
    public int updateHobby(Hobby hobby, HobbyDTO hobbyDTO, MultipartFile[] files, List<ImageUrlsDTO> urls) {



        List<HobbyKeyword> hobbyKeywordList = new ArrayList<>();
        List<HobbyImage> hobbyImages = new ArrayList<>();


        hobby.setHobbyTitle(hobbyDTO.getHobbyTitle());     //제목
        hobby.setHobbyPrice(hobbyDTO.getHobbyPrice());     //가격
        hobby.setTutorCode(hobbyDTO.getTutorCode());       //강사코드
        hobby.setClose(hobbyDTO.getClose());               //마감여부
        hobby.setMaxPersonnel(hobbyDTO.getMaxPersonnel()); //최대인원
        hobby.setCategoryCode(hobbyDTO.getCategoryCode()); //카테고리
        hobby.setLocalCode(hobbyDTO.getLocalCode());       //지역
        hobby.setIntro(hobbyDTO.getIntro());               //소개
        hobby.setTutorIntro(hobbyDTO.getTutorIntro());     //강사소개
        hobby.setDate(hobbyDTO.getDate());                 //날짜
        hobby.setStartTime(hobbyDTO.getStartTime());       //시작시간
        hobby.setEndTime(hobbyDTO.getEndTime());           //끝나는 시간
        hobby.setClosingDate(hobbyDTO.getClosingDate());   //마감날짜
        hobby.setHobbyPlace(hobbyDTO.getHobbyPlace());        //상세지역


//

        for (int i = 0; i < hobbyDTO.getKeywordDTOList().size(); i++) {
                    Keyword findKeyword = keywordRepository.findById(hobbyDTO.getKeywordDTOList().get(i).getKeywordCode());
                    hobbyKeywordList.add(new HobbyKeyword(new HobbyPk(hobby.getHobbyCode(), findKeyword.getKeywordCode()), hobby, findKeyword));
                }


        try {

            if (Objects.isNull(files) && urls.size() != 0) {
                for (int i = 0; i < urls.size(); i++) {
                    HobbyImage image = new HobbyImage();
                    image.setHobbyCode(hobby.getHobbyCode());
                    image.setPath(urls.get(i).getPath());
                    hobbyImages.add(image);
                }
            } else if (urls.size() == 0) {
                ResponseEntity res = ImageApi.multiImages(files);
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(res.getBody().toString());
                JSONArray jsonArray1 = (JSONArray) jsonObject.get("fileInfo");
                for (int i = 0; i < jsonArray1.size(); i++) {
                    HobbyImage image = new HobbyImage();
                    JSONObject obj = (JSONObject) jsonArray1.get(i);
                    image.setHobbyCode(hobby.getHobbyCode());
                    image.setName(((String) obj.get("originalname")));
                    image.setPath(((String) obj.get("path")).replace("uploads\\", ""));
                    hobbyImages.add(image);
                }
            } else {
                for (int i = 0; i < urls.size(); i++) {
                    HobbyImage image = new HobbyImage();
                    image.setHobbyCode(hobby.getHobbyCode());
                    image.setPath(urls.get(i).getPath());
                    hobbyImages.add(image);
                }
                ResponseEntity res = ImageApi.multiImages(files);
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(res.getBody().toString());
                JSONArray jsonArray1 = (JSONArray) jsonObject.get("fileInfo");
                for (int i = 0; i < jsonArray1.size(); i++) {
                    HobbyImage image = new HobbyImage();
                    JSONObject obj = (JSONObject) jsonArray1.get(i);
                    image.setHobbyCode(hobby.getHobbyCode());
                    image.setName(((String) obj.get("originalname")));
                    image.setPath(((String) obj.get("path")).replace("uploads\\", ""));
                    hobbyImages.add(image);
                }

            }


            hobby.getHobbyKeywordList().stream().forEach(hobbyKeyword -> {
                hobbyKeywordRepository.delete(hobbyKeyword);
            });


            hobby.getHobbyKeywordList().clear();

            hobbyKeywordRepository.deleteAllInBatch(hobby.getHobbyKeywordList());
            storageRepository.deleteAllInBatch(hobby.getHobbyImages());
            hobbyKeywordRepository.flush();
            storageRepository.flush();


            storageRepository.saveAll(hobbyImages);
            hobbyKeywordRepository.saveAll(hobbyKeywordList);


        } catch (IOException e) {
            return 0;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Hobby findHobby = hobbyRepository.save(hobby);

        return 1;
    }

    public HobbyJoin findJoin(int hobbyCode, int userNo) {
        HobbyJoin hobbyJoin = hobbyJoinRepository.findByHobbyCodeAndUserNo(hobbyCode, userNo);

        return hobbyJoin;

    }

    @Transactional
    public int joinHobby(HobbyJoin join) {
        HobbyJoin hobbyJoin = hobbyJoinRepository.save(join);

        if (Objects.isNull(hobbyJoin)) {
            return 0;
        } else {
            return 1;
        }

    }

    @Transactional
    public int unJoinHobby(HobbyJoin hobbyJoin) {

        hobbyJoinRepository.deleteById(hobbyJoin.getJoinNum());
        HobbyJoin findHobbyJoin = hobbyJoinRepository.findById(hobbyJoin.getJoinNum());

        if (Objects.isNull(findHobbyJoin)) {
            return 1;
        } else {
            return 2;
        }

    }

    public List<HobbyJoin> findAllJoin(int hobbyCode) {
        List<HobbyJoin> hobbyJoins = hobbyJoinRepository.findAllByHobbyCode(hobbyCode);

        return hobbyJoins;

    }

    public int registReview(HobbyReview hobbyReview) {
        HobbyReview findReview = hobbyReviewRepository.save(hobbyReview);

        if (Objects.isNull(findReview)) {
            return 0;
        } else {
            return 1;
        }
    }

    @Transactional
    public int closeHobby(int hobbyCode) {
        Hobby hobby = hobbyRepository.findById(hobbyCode);
        if (Objects.isNull(hobby)) {
            return 0;
        }

        hobby.setClose("Y");
        return 1;


    }

    public List<HobbyReview> findAllReview(int hobbyCode) {

        List<HobbyReview> hobbyReviews = hobbyReviewRepository.findAllByHobbyCode(hobbyCode);

        return hobbyReviews;
    }

    public HobbyReview findByIdReview(int hobbyCode, int userNo) {
        HobbyReview hobbyReview = hobbyReviewRepository.findAllByHobbyCodeAndUserNo(hobbyCode, userNo);
        return hobbyReview;

    }

    public HobbyReview findByReviewCode(int reviewCode) {
        HobbyReview hobbyReview = hobbyReviewRepository.findById(reviewCode);

        return hobbyReview;
    }


    @Transactional
    public int deleteByReviewCode(HobbyReview hobbyReview) {
        HobbyReview findReiview = hobbyReviewRepository.save(hobbyReview);


        if (findReiview.getReviewStatus().equals("N")) {
            return 1;
        } else {
            return 0;
        }
    }

    @Transactional
    public int updateReview(HobbyReviewDTO hobbyReviewDTO) {
        HobbyReview findReview = hobbyReviewRepository.findById(hobbyReviewDTO.getReviewCode());

        findReview.setContent(hobbyReviewDTO.getContent());
        findReview.setScore(hobbyReviewDTO.getScore());

        if (findReview.getContent().equals(hobbyReviewDTO.getContent()) && findReview.getScore() == hobbyReviewDTO.getScore()) {
            return 1;
        } else {
            return 0;
        }


    }

    public List<HobbyGetDTO> findByCategoryCode(int categoryCode, Pageable pageable) {
        List<Hobby> hobbies = hobbyRepository.findByCategoryCode(categoryCode, pageable);

        List<HobbyGetDTO> hobbyGetDTOS = hobbies.stream().map(m -> new HobbyGetDTO(m)).collect(Collectors.toList());

        for (int i = 0; i < hobbies.size(); i++) {
            List<Keyword> keyword = new ArrayList<>();
            List<HobbyKeywordDTO> keywordDTOList = new ArrayList<>();
            for (int j = 0; j < hobbies.get(i).getHobbyKeywordList().size(); j++) {
                keyword.add(hobbies.get(i).getHobbyKeywordList().get(j).getKeyword());
                keywordDTOList = keyword.stream().map(m -> new HobbyKeywordDTO(m)).collect(Collectors.toList());
            }
            hobbyGetDTOS.get(i).setKeyword(keywordDTOList);
        }

        return hobbyGetDTOS;
    }

    public List<HobbyJoin> findByJoin(int hobbyCode) {
        List<HobbyJoin> hobbyJoins = hobbyJoinRepository.findAllByHobbyCode(hobbyCode);

        return hobbyJoins;
    }

    public List<HobbyGetDTO> findByLocalCode(int localCode, Pageable pageable) {
        List<Hobby> hobbies = hobbyRepository.findByLocalCode(localCode, pageable);
        List<HobbyGetDTO> hobbyGetDTOS = hobbies.stream().map(m -> new HobbyGetDTO(m)).collect(Collectors.toList());

        for (int i = 0; i < hobbies.size(); i++) {
            List<Keyword> keyword = new ArrayList<>();
            List<HobbyKeywordDTO> keywordDTOList = new ArrayList<>();
            for (int j = 0; j < hobbies.get(i).getHobbyKeywordList().size(); j++) {
                keyword.add(hobbies.get(i).getHobbyKeywordList().get(j).getKeyword());
                keywordDTOList = keyword.stream().map(m -> new HobbyKeywordDTO(m)).collect(Collectors.toList());
            }
            hobbyGetDTOS.get(i).setKeyword(keywordDTOList);
        }


        return hobbyGetDTOS;
    }

    public List<HobbyGetDTO> findByCategoryCodeAndLocalCode(int categoryCode, int localCode, Pageable pageable) {
        List<Hobby> hobbies = hobbyRepository.findByCategoryCodeAndLocalCode(categoryCode, localCode, pageable);
        List<HobbyGetDTO> hobbyGetDTOS = hobbies.stream().map(m -> new HobbyGetDTO(m)).collect(Collectors.toList());

        for (int i = 0; i < hobbies.size(); i++) {
            List<Keyword> keyword = new ArrayList<>();
            List<HobbyKeywordDTO> keywordDTOList = new ArrayList<>();
            for (int j = 0; j < hobbies.get(i).getHobbyKeywordList().size(); j++) {
                keyword.add(hobbies.get(i).getHobbyKeywordList().get(j).getKeyword());
                keywordDTOList = keyword.stream().map(m -> new HobbyKeywordDTO(m)).collect(Collectors.toList());
            }
            hobbyGetDTOS.get(i).setKeyword(keywordDTOList);
        }


        return hobbyGetDTOS;
    }

    public ReviewAnswer registReviewAnswer(ReviewAnswerDTO reviewAnswerDTO) {
        ReviewAnswer reviewAnswer = new ReviewAnswer().tutorCode(reviewAnswerDTO.getTutorCode()).reviewCode(reviewAnswerDTO.getReviewCode()).content(reviewAnswerDTO.getContent()).builder();


        ReviewAnswer findReviewAnswer = reviewAnswerRepository.save(reviewAnswer);

        return findReviewAnswer;

    }

    public ReviewAnswer reviewAnswerFindByRevieCode(int reviewCode) {
        ReviewAnswer reviewAnswer = reviewAnswerRepository.findAllByReviewCode(reviewCode);

        return reviewAnswer;
    }

//    public List<String> findEncodedImages(int hobbyCode) throws IOException{
//        List<String> images = new ArrayList<>();
//
//        List<HobbyImage> hobbyImage = storageRepository.findByhobbyCode(hobbyCode);
//
//        for(int i =0 ; i<hobbyImage.size();i++){
//            byte[] fileContent = ImageUtils.decompressImage(hobbyImage.get(i).getImageDate()); // file을 byte로 변경
//            String encodedString = Base64.getEncoder().encodeToString(fileContent);  // byte를 base64로 encode
//            images.add(encodedString);
//        }
//        return images;
//    }

    public List<Hobby> findByAll() {
        List<Hobby> hobbyList = hobbyRepository.findAll();

        return hobbyList;
    }

    public List<HobbyGetDTO> findByTutorCode(Pageable pageable, int userNo) {

        List<Hobby> hobbyList = hobbyRepository.findByTutorCodeOrderByHobbyCodeDesc(userNo,pageable);
        List<HobbyGetDTO> hobbyGetDTOS = hobbyList.stream().map(m -> new HobbyGetDTO(m)).collect(Collectors.toList());

        for (int i = 0; i < hobbyList.size(); i++) {
            List<Keyword> keyword = new ArrayList<>();
            List<HobbyKeywordDTO> keywordDTOList = new ArrayList<>();
            for (int j = 0; j < hobbyList.get(i).getHobbyKeywordList().size(); j++) {
                keyword.add(hobbyList.get(i).getHobbyKeywordList().get(j).getKeyword());
                keywordDTOList = keyword.stream().map(m -> new HobbyKeywordDTO(m)).collect(Collectors.toList());
            }
            hobbyGetDTOS.get(i).setKeyword(keywordDTOList);
        }

        return hobbyGetDTOS;
    }

    public List<Hobby> findByTutorCode(int tutorCode) {
        List<Hobby> hobbyList = hobbyRepository.findByTutorCode(tutorCode);

        return hobbyList;
    }

    public List<HobbyGetDTO> findHobbyTitleContatining(final Pageable pageable , String name) {

        List<Hobby> hobbyList = hobbyRepository.findHobbyByHobbyTitleContaining(pageable, name);
        List<HobbyGetDTO> hobbyGetDTOS = hobbyList.stream().map(m -> new HobbyGetDTO(m)).collect(Collectors.toList());

        for (int i = 0; i < hobbyList.size(); i++) {
            List<Keyword> keyword = new ArrayList<>();
            List<HobbyKeywordDTO> keywordDTOList = new ArrayList<>();
            for (int j = 0; j < hobbyList.get(i).getHobbyKeywordList().size(); j++) {
                keyword.add(hobbyList.get(i).getHobbyKeywordList().get(j).getKeyword());
                keywordDTOList = keyword.stream().map(m -> new HobbyKeywordDTO(m)).collect(Collectors.toList());
            }
            hobbyGetDTOS.get(i).setKeyword(keywordDTOList);
        }

        return hobbyGetDTOS;
    }


    public List<Hobby> findByHobbyTitleContatining(String name) {
        List<Hobby> hobbyList = hobbyRepository.findByHobbyTitleContaining(name);
        return hobbyList;
    }
}
