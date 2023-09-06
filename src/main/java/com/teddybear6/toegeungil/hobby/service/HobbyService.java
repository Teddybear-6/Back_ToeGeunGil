package com.teddybear6.toegeungil.hobby.service;

import com.teddybear6.toegeungil.hobby.dto.HobbyDTO;
import com.teddybear6.toegeungil.hobby.dto.HobbyGetDTO;
import com.teddybear6.toegeungil.hobby.dto.HobbyKeywordDTO;
import com.teddybear6.toegeungil.hobby.entity.*;
import com.teddybear6.toegeungil.hobby.repository.*;
import com.teddybear6.toegeungil.hobby.utils.ImageUtils;
import com.teddybear6.toegeungil.keyword.entity.Keyword;
import com.teddybear6.toegeungil.keyword.repository.KeywordRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class HobbyService {

    private final StorageRepository storageRepository;
    private final KeywordRepository keywordRepository;
    private final HobbyRepository hobbyRepository;

    private final HobbyKeywordRepository hobbyKeywordRepository;

    private final HobbyJoinRepository hobbyJoinRepository;

    private final HobbyReviewRepository hobbyReviewRepository;


    public HobbyService(StorageRepository storageRepository, KeywordRepository keywordRepository, HobbyRepository hobbyRepository, HobbyKeywordRepository hobbyKeywordRepository, HobbyJoinRepository hobbyJoinRepository, HobbyReviewRepository hobbyReviewRepository) {
        this.storageRepository = storageRepository;
        this.keywordRepository = keywordRepository;
        this.hobbyRepository = hobbyRepository;
        this.hobbyKeywordRepository = hobbyKeywordRepository;
        this.hobbyJoinRepository = hobbyJoinRepository;
        this.hobbyReviewRepository = hobbyReviewRepository;
    }

    public String uploadImage(MultipartFile file) throws IOException {

        HobbyImage image = new HobbyImage();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setImageDate(ImageUtils.compressImage(file.getBytes()));

        HobbyImage findImage = storageRepository.save(image);
        if (findImage != null) {
            return file.getOriginalFilename();
        }
        return null;

    }


    public byte[] downloadImage(String fileName) {
        HobbyImage image = storageRepository.findByName(fileName)
                .orElseThrow(RuntimeException::new);

        return ImageUtils.decompressImage(image.getImageDate());
    }

    @Transactional
    public int registHobby(HobbyDTO hobbyDTO, List<MultipartFile> files) throws IOException {
        Hobby hobby = new Hobby(hobbyDTO);
        List<HobbyKeywordDTO> keyword = hobbyDTO.getKeywordDTOList();
        List<HobbyKeyword> hobbyKeywordList = new ArrayList<>();
        List<HobbyImage> hobbyImages = new ArrayList<>();

        for (int i = 0; i < keyword.size(); i++) {
            Keyword findKeyword = keywordRepository.findById(keyword.get(i).getKeywordCode());
            hobbyKeywordList.add(new HobbyKeyword(new HobbyPk(hobby.getHobbyCode(), findKeyword.getKeywordCode()), hobby, findKeyword));
        }

        hobby.setHobbyKeywordList(hobbyKeywordList);
        Hobby findHobby = hobbyRepository.save(hobby);
        for (int i = 0; i < files.size(); i++) {
            HobbyImage image = new HobbyImage();
            image.setHobbyCode(findHobby.getHobbyCode());
            image.setName(files.get(i).getOriginalFilename());
            image.setType(files.get(i).getContentType());
            image.setImageDate(ImageUtils.compressImage(files.get(i).getBytes()));
            hobbyImages.add(image);
        }
        List<HobbyImage> findImages = storageRepository.saveAll(hobbyImages);
        System.out.println(findHobby);
        System.out.println(findImages.get(0).getHobbyCode());
        if (Objects.isNull(findHobby)) {
            return 0;
        } else {
            return 1;
        }


    }

    public List<HobbyGetDTO> findAll(final Pageable pageable) {

        List<Hobby> hobbyList = hobbyRepository.findAll(pageable).getContent();
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

        System.out.println(hobbyGetDTOS);
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
    public int updateHobby(Hobby hobby, HobbyDTO hobbyDTO, List<MultipartFile> files) {

        List<HobbyKeywordDTO> keyword = hobbyDTO.getKeywordDTOList();
        List<HobbyKeyword> hobbyKeywordList = new ArrayList<>();
        List<HobbyImage> hobbyImages = new ArrayList<>();

        hobby.setHobbyTitle(hobbyDTO.getHobbyTitle());     //제목
        hobby.setHobbyPrice(hobbyDTO.getHobbyPrice());     //가격
        hobby.setClose(hobbyDTO.getClose());               //마감여부
        hobby.setCategoryCode(hobbyDTO.getCategoryCode()); //카테고리
        hobby.setLocalCode(hobbyDTO.getLocalCode());       //지역
        hobby.setIntro(hobbyDTO.getIntro());               //소개
        hobby.setTutorIntro(hobbyDTO.getTutorIntro());     //강사소개
        hobby.setDate(hobbyDTO.getDate());                 //날짜
        hobby.setStartTime(hobbyDTO.getStartTime());       //시작시간
        hobby.setEndTime(hobbyDTO.getEndTime());           //끝나는 시간


        hobbyKeywordRepository.deleteAllInBatch(hobby.getHobbyKeywordList());
        storageRepository.deleteAllByHobbyCode(hobby.getHobbyCode());

        for (int i = 0; i < keyword.size(); i++) {
            Keyword findKeyword = keywordRepository.findById(keyword.get(i).getKeywordCode());
            hobbyKeywordList.add(new HobbyKeyword(new HobbyPk(hobby.getHobbyCode(), findKeyword.getKeywordCode()), hobby, findKeyword));

        }


        hobby.setHobbyKeywordList(hobbyKeywordList);
        System.out.println(hobby.getHobbyKeywordList());


        try {
            for (int i = 0; i < files.size(); i++) {
                HobbyImage updateImage = new HobbyImage();
                updateImage.setHobbyCode(hobby.getHobbyCode());
                updateImage.setName(files.get(i).getOriginalFilename());
                updateImage.setType(files.get(i).getContentType());
                updateImage.setImageDate(ImageUtils.compressImage(files.get(i).getBytes()));
                hobbyImages.add(updateImage);
            }
        } catch (IOException e) {
            return 0;
        }


        hobby.setHobbyImages(hobbyImages);
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
}