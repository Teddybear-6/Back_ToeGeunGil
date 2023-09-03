package com.teddybear6.toegeungil.hobby.service;

import com.teddybear6.toegeungil.hobby.entity.HobbyImage;
import com.teddybear6.toegeungil.hobby.repository.StorageRepository;
import com.teddybear6.toegeungil.hobby.utils.ImageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class HobbyService {

    private  final  StorageRepository storageRepository;

    public HobbyService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public String uploadImage(MultipartFile file) throws IOException {

        HobbyImage image = new HobbyImage();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setImageDate(ImageUtils.compressImage(file.getBytes()));

        HobbyImage findImage = storageRepository.save(image);
        if(findImage!=null){
            return file.getOriginalFilename();
        }
        return null;

    }



}
