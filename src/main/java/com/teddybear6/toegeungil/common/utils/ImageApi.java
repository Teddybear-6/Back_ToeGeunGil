package com.teddybear6.toegeungil.common.utils;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

@Component
public class ImageApi {

    //단일 파일 업로드
    public ResponseEntity<?> singleImage(MultipartFile file) throws IOException{
        String host = "http://localhost:9000/upload";

        MultiValueMap<String , Object>  body = new LinkedMultiValueMap<>();

        ByteArrayResource contentsAsResource = new ByteArrayResource(file.getBytes()){
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        body.add("img",contentsAsResource);

        HttpHeaders httpHeaders = new HttpHeaders();

        MediaType mediaType = new MediaType("multipart" ,"form-data", Charset.forName("UTF-8"));
        httpHeaders.setContentType(mediaType);

        HttpEntity<MultiValueMap<String,Object>> request = new HttpEntity<>(body,httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity res = restTemplate.postForEntity(host,request,String.class);
        
        return ResponseEntity.ok("성공");



    }


    public  ResponseEntity<?> arrays(MultipartFile[] files) throws IOException{

        String host = "http://localhost:9000/uploads";
        MultiValueMap<String , Object> body = new LinkedMultiValueMap<>();

        Arrays.stream(files).forEach(file->{
            try{
                ByteArrayResource resource = new ByteArrayResource(file.getBytes()){
                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }
                };
                body.add("imgs",resource);
            }catch (IOException e){
                e.printStackTrace();
            }
        });


        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType mediaType = new MediaType("multipart" , "form-data",Charset.forName("UTF-8"));
        httpHeaders.setContentType(mediaType);

        HttpEntity<MultiValueMap<String,Object>> request = new HttpEntity<>(body,httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity res = restTemplate.exchange(host, HttpMethod.POST,request,String.class);

        return res;
    }



}
