package com.teddybear6.toegeungil.hobby.service;

import com.teddybear6.toegeungil.hobby.dto.HobbyDTO;
import com.teddybear6.toegeungil.hobby.dto.HobbyGetDTO;
import com.teddybear6.toegeungil.hobby.dto.HobbyKeywordDTO;
import com.teddybear6.toegeungil.hobby.entity.Hobby;
import com.teddybear6.toegeungil.hobby.entity.HobbyImage;
import com.teddybear6.toegeungil.hobby.entity.HobbyKeyword;
import com.teddybear6.toegeungil.hobby.entity.HobbyPk;
import com.teddybear6.toegeungil.hobby.repository.HobbyRepository;
import com.teddybear6.toegeungil.hobby.repository.StorageRepository;
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


    public HobbyService(StorageRepository storageRepository, KeywordRepository keywordRepository, HobbyRepository hobbyRepository) {
        this.storageRepository = storageRepository;
        this.keywordRepository = keywordRepository;
        this.hobbyRepository = hobbyRepository;
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

    public List<HobbyGetDTO> findAll(final Pageable pageable ) {

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

    public byte[] findMainImage(int hobbyCode) {
        List<HobbyImage> hobbyImage = storageRepository.findByhobbyCode(hobbyCode);
        if (hobbyImage.size() == 0) {
            System.out.println(hobbyImage.size() == 0);
            return new byte[0];
        }

        return ImageUtils.decompressImage(hobbyImage.get(0).getImageDate());
    }
}
