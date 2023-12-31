package com.teddybear6.toegeungil.hobby.service;

import com.teddybear6.toegeungil.category.repository.CategoryRepository;
import com.teddybear6.toegeungil.common.utils.ImageApi;
import com.teddybear6.toegeungil.hobby.dto.*;
import com.teddybear6.toegeungil.hobby.entity.*;
import com.teddybear6.toegeungil.hobby.repository.*;
import com.teddybear6.toegeungil.keyword.entity.Keyword;
import com.teddybear6.toegeungil.keyword.repository.KeywordRepository;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
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
    private final CategoryRepository categoryRepository;


    public HobbyService(StorageRepository storageRepository, KeywordRepository keywordRepository, HobbyRepository hobbyRepository, HobbyKeywordRepository hobbyKeywordRepository, ReviewAnswerRepository reviewAnswerRepository, HobbyJoinRepository hobbyJoinRepository, HobbyReviewRepository hobbyReviewRepository, CategoryRepository categoryRepository) {
        this.storageRepository = storageRepository;
        this.keywordRepository = keywordRepository;
        this.hobbyRepository = hobbyRepository;
        this.hobbyKeywordRepository = hobbyKeywordRepository;
        this.reviewAnswerRepository = reviewAnswerRepository;
        this.hobbyJoinRepository = hobbyJoinRepository;
        this.hobbyReviewRepository = hobbyReviewRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public int registHobby(HobbyDTO hobbyDTO, MultipartFile[] files) throws IOException, ParseException {
        hobbyDTO.setCrateDate(new Date());
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


    public Map<String, Object> findAll(final Pageable pageable) {

        Page<Hobby> hobbyListPage = hobbyRepository.findAllByOrderByHobbyCodeDesc(pageable);
        System.out.println("확인1");

        List<Hobby> hobbyList =hobbyListPage.getContent();



        List<HobbyGetDTO> hobbyGetDTOS = hobbyList.stream().map(m -> new HobbyGetDTO(m)).collect(Collectors.toList());

        for (int i = 0; i < hobbyList.size(); i++) {
            List<Keyword> keyword = new ArrayList<>();
            List<HobbyKeywordDTO> keywordDTOList = new ArrayList<>();
            for (int j = 0; j < hobbyList.get(i).getHobbyKeywordList().size(); j++) {
                keyword.add(hobbyList.get(i).getHobbyKeywordList().get(j).getKeyword());
                keywordDTOList = keyword.stream().map(m -> new HobbyKeywordDTO(m)).collect(Collectors.toList());
            }

            hobbyGetDTOS.get(i).setCategoryName(categoryRepository.findById(hobbyGetDTOS.get(i).getCategoryCode()).getCategoryName());
            List<HobbyImage> hobbyImages = hobbyList.get(i).getHobbyImages();

            ImageIdDTO imageIdDTOS = (new ImageIdDTO(hobbyImages.get(0).getId(), hobbyImages.get(0).getPath(), hobbyImages.get(0).getName(), hobbyImages.get(0).getHobbyCode()));
            hobbyGetDTOS.get(i).setImageIdDTO(imageIdDTOS);

            hobbyGetDTOS.get(i).setKeyword(keywordDTOList);
        }
        Map allhobbyMap = new HashMap<>();
        allhobbyMap.put("value",hobbyGetDTOS);
        allhobbyMap.put("size", hobbyListPage.getTotalElements());
        return allhobbyMap;
    }






    public List<HobbyImage> findMainImage(int hobbyCode) {
        List<HobbyImage> hobbyImage = storageRepository.findByhobbyCode(hobbyCode);

        return hobbyImage;
    }

    public  HobbyDTO findByIddetail(int hobbyCode) {
        Hobby hobby = hobbyRepository.findById(hobbyCode);
        HobbyDTO hobbyDTO = new HobbyDTO(hobby);
        List<Keyword> keyword = new ArrayList<>();
        for (int i = 0; i < hobby.getHobbyKeywordList().size(); i++) {
            keyword.add(hobby.getHobbyKeywordList().get(i).getKeyword());
        }
        List<HobbyImage> hobbyImages = hobby.getHobbyImages();

        List<ImageIdDTO> imageIdDTOS = new ArrayList<>();

        for (int i = 0; i < hobbyImages.size(); i++) {

            imageIdDTOS.add(new ImageIdDTO(hobbyImages.get(i).getId(), hobbyImages.get(i).getPath(), hobbyImages.get(i).getName(), hobbyImages.get(i).getHobbyCode()));

        }

        List<HobbyKeywordDTO> hobbyKeywordDTO = keyword.stream().map(m -> new HobbyKeywordDTO(m)).collect(Collectors.toList());
        hobbyDTO.setKeywordDTOList(hobbyKeywordDTO);
        hobbyDTO.setImageId(imageIdDTOS);

        return hobbyDTO;


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
        hobby.setUpdateDate(new Date());

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
        hobbyReview.setCrateDate(new Date());
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
    public int updateReview(HobbyReview hobbyReview , HobbyReviewDTO hobbyReviewDTO ) {
        hobbyReview.setUpdateDate(new Date());


        hobbyReview.setContent(hobbyReviewDTO.getContent());
//        hobbyReview.setScore(hobbyReviewDTO.getScore());

        if (hobbyReview.getContent().equals(hobbyReviewDTO.getContent())) {
            return 1;
        } else {
            return 0;
        }


    }

    public Map<String,Object> findByCategoryCode(int categoryCode, Pageable pageable) {
        Page<Hobby> hobbyListPage = hobbyRepository.findByCategoryCode(categoryCode, pageable);
        List<Hobby> hobbyList =hobbyListPage.getContent();
        System.out.println("확인1");

        List<HobbyGetDTO> hobbyGetDTOS = hobbyList.stream().map(m -> new HobbyGetDTO(m)).collect(Collectors.toList());

        for (int i = 0; i < hobbyList.size(); i++) {
            List<Keyword> keyword = new ArrayList<>();
            List<HobbyKeywordDTO> keywordDTOList = new ArrayList<>();
            for (int j = 0; j < hobbyList.get(i).getHobbyKeywordList().size(); j++) {
                keyword.add(hobbyList.get(i).getHobbyKeywordList().get(j).getKeyword());
                keywordDTOList = keyword.stream().map(m -> new HobbyKeywordDTO(m)).collect(Collectors.toList());
            }
            hobbyGetDTOS.get(i).setKeyword(keywordDTOList);
            List<HobbyImage> hobbyImages = hobbyList.get(i).getHobbyImages();
            hobbyGetDTOS.get(i).setCategoryName(categoryRepository.findById(hobbyGetDTOS.get(i).getCategoryCode()).getCategoryName());
            ImageIdDTO imageIdDTOS = (new ImageIdDTO(hobbyImages.get(0).getId(), hobbyImages.get(0).getPath(), hobbyImages.get(0).getName(), hobbyImages.get(0).getHobbyCode()));
            hobbyGetDTOS.get(i).setImageIdDTO(imageIdDTOS);
        }
        Map<String,Object> categotyHobby = new HashMap<>();
        categotyHobby.put("value",hobbyGetDTOS);
        categotyHobby.put("size",hobbyListPage.getTotalElements());
        return categotyHobby;
    }

    public List<HobbyJoin> findByJoin(int hobbyCode) {
        List<HobbyJoin> hobbyJoins = hobbyJoinRepository.findAllByHobbyCode(hobbyCode);

        return hobbyJoins;
    }

    public Map<String,Object> findByLocalCode(int localCode, Pageable pageable) {
        Page<Hobby> hobbyListPage = hobbyRepository.findByLocalCode(localCode, pageable);
        List<Hobby> hobbyList =hobbyListPage.getContent();
        List<HobbyGetDTO> hobbyGetDTOS = hobbyList.stream().map(m -> new HobbyGetDTO(m)).collect(Collectors.toList());

        for (int i = 0; i < hobbyList.size(); i++) {
            List<Keyword> keyword = new ArrayList<>();
            List<HobbyKeywordDTO> keywordDTOList = new ArrayList<>();
            for (int j = 0; j < hobbyList.get(i).getHobbyKeywordList().size(); j++) {
                keyword.add(hobbyList.get(i).getHobbyKeywordList().get(j).getKeyword());
                keywordDTOList = keyword.stream().map(m -> new HobbyKeywordDTO(m)).collect(Collectors.toList());
            }
            List<HobbyImage> hobbyImages = hobbyList.get(i).getHobbyImages();
            hobbyGetDTOS.get(i).setCategoryName(categoryRepository.findById(hobbyGetDTOS.get(i).getCategoryCode()).getCategoryName());
            ImageIdDTO imageIdDTOS = (new ImageIdDTO(hobbyImages.get(0).getId(), hobbyImages.get(0).getPath(), hobbyImages.get(0).getName(), hobbyImages.get(0).getHobbyCode()));
            hobbyGetDTOS.get(i).setImageIdDTO(imageIdDTOS);
            hobbyGetDTOS.get(i).setKeyword(keywordDTOList);
        }
        Map<String,Object> localHobby = new HashMap<>();
        localHobby.put("value",hobbyGetDTOS);
        localHobby.put("size",hobbyListPage.getTotalElements());

        return localHobby;
    }

    public Map<String,Object> findByCategoryCodeAndLocalCode(int categoryCode, int localCode, Pageable pageable) {
        Page<Hobby> hobbyListPage = hobbyRepository.findByCategoryCodeAndLocalCode(categoryCode, localCode, pageable);
        List<Hobby> hobbyList =hobbyListPage.getContent();
        List<HobbyGetDTO> hobbyGetDTOS = hobbyList.stream().map(m -> new HobbyGetDTO(m)).collect(Collectors.toList());

        for (int i = 0; i < hobbyList.size(); i++) {
            List<Keyword> keyword = new ArrayList<>();
            List<HobbyKeywordDTO> keywordDTOList = new ArrayList<>();
            for (int j = 0; j < hobbyList.get(i).getHobbyKeywordList().size(); j++) {
                keyword.add(hobbyList.get(i).getHobbyKeywordList().get(j).getKeyword());
                keywordDTOList = keyword.stream().map(m -> new HobbyKeywordDTO(m)).collect(Collectors.toList());
            }
            List<HobbyImage> hobbyImages = hobbyList.get(i).getHobbyImages();

            hobbyGetDTOS.get(i).setCategoryName(categoryRepository.findById(hobbyGetDTOS.get(i).getCategoryCode()).getCategoryName());

            ImageIdDTO imageIdDTOS = (new ImageIdDTO(hobbyImages.get(0).getId(), hobbyImages.get(0).getPath(), hobbyImages.get(0).getName(), hobbyImages.get(0).getHobbyCode()));
            hobbyGetDTOS.get(i).setImageIdDTO(imageIdDTOS);
            hobbyGetDTOS.get(i).setKeyword(keywordDTOList);
        }

        Map<String,Object> categoryLocalHobby = new HashMap<>();
        categoryLocalHobby.put("value",hobbyGetDTOS);
        categoryLocalHobby.put("size",hobbyListPage.getTotalElements());
        return categoryLocalHobby;
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




    public Map<String,Object> findByTutorCode(Pageable pageable, int userNo) {

        Page<Hobby> hobbyListPage = hobbyRepository.findByTutorCodeOrderByHobbyCodeDesc(userNo,pageable);
        List<Hobby> hobbyList =hobbyListPage.getContent();

        List<HobbyGetDTO> hobbyGetDTOS = hobbyList.stream().map(m -> new HobbyGetDTO(m)).collect(Collectors.toList());

        for (int i = 0; i < hobbyList.size(); i++) {
            List<Keyword> keyword = new ArrayList<>();
            List<HobbyKeywordDTO> keywordDTOList = new ArrayList<>();
            for (int j = 0; j < hobbyList.get(i).getHobbyKeywordList().size(); j++) {
                keyword.add(hobbyList.get(i).getHobbyKeywordList().get(j).getKeyword());
                keywordDTOList = keyword.stream().map(m -> new HobbyKeywordDTO(m)).collect(Collectors.toList());
            }
            List<HobbyImage> hobbyImages = hobbyList.get(i).getHobbyImages();
            hobbyGetDTOS.get(i).setCategoryName(categoryRepository.findById(hobbyGetDTOS.get(i).getCategoryCode()).getCategoryName());
            ImageIdDTO imageIdDTOS = (new ImageIdDTO(hobbyImages.get(0).getId(), hobbyImages.get(0).getPath(), hobbyImages.get(0).getName(), hobbyImages.get(0).getHobbyCode()));
            hobbyGetDTOS.get(i).setImageIdDTO(imageIdDTOS);
            hobbyGetDTOS.get(i).setKeyword(keywordDTOList);
        }
        Map<String, Object> tutorhobbyMap = new HashMap<>();
        tutorhobbyMap.put("value",hobbyGetDTOS);
        tutorhobbyMap.put("size",hobbyListPage.getTotalElements());
        return tutorhobbyMap;
    }



    public Map<String, Object>  findHobbyTitleContatining(final Pageable pageable , String name) {

        Page<Hobby> hobbyListPage = hobbyRepository.findHobbyByHobbyTitleContaining(pageable, name);
        List<Hobby> hobbyList =hobbyListPage.getContent();
        List<HobbyGetDTO> hobbyGetDTOS = hobbyList.stream().map(m -> new HobbyGetDTO(m)).collect(Collectors.toList());

        for (int i = 0; i < hobbyList.size(); i++) {
            List<Keyword> keyword = new ArrayList<>();
            List<HobbyKeywordDTO> keywordDTOList = new ArrayList<>();
            for (int j = 0; j < hobbyList.get(i).getHobbyKeywordList().size(); j++) {
                keyword.add(hobbyList.get(i).getHobbyKeywordList().get(j).getKeyword());
                keywordDTOList = keyword.stream().map(m -> new HobbyKeywordDTO(m)).collect(Collectors.toList());
            }
            List<HobbyImage> hobbyImages = hobbyList.get(i).getHobbyImages();
            hobbyGetDTOS.get(i).setCategoryName(categoryRepository.findById(hobbyGetDTOS.get(i).getCategoryCode()).getCategoryName());
            ImageIdDTO imageIdDTOS = (new ImageIdDTO(hobbyImages.get(0).getId(), hobbyImages.get(0).getPath(), hobbyImages.get(0).getName(), hobbyImages.get(0).getHobbyCode()));
            hobbyGetDTOS.get(i).setImageIdDTO(imageIdDTOS);
            hobbyGetDTOS.get(i).setKeyword(keywordDTOList);
        }
        Map searchHobby = new HashMap<>();
        searchHobby.put("value",hobbyGetDTOS);
        searchHobby.put("size",hobbyListPage.getTotalElements());
        return searchHobby;
    }





    public Hobby findById(int hobbyCode) {
        Hobby hobby = hobbyRepository.findById(hobbyCode);
        return hobby;
    }

}
