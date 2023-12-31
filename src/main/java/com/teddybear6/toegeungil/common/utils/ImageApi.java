package com.teddybear6.toegeungil.common.utils;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class ImageApi {

    public static  String url;

    @Value("${image.url}")
    public void setUrl(String value){
        url=value;
    }

    //단일 파일 업로드
    public static ResponseEntity<?> singleImage(MultipartFile file) throws IOException, ParseException {
        String host = url+"upload";

        MultiValueMap<String , Object>  body = new LinkedMultiValueMap<>();
        System.out.println("================");
        System.out.println(host);
        System.out.println("=================");
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

        return res;

    }


    public static ResponseEntity<?> multiImages(MultipartFile[] files) throws IOException, ParseException {
        String host = url+"uploads";
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

//
//        JSONParser parser = new JSONParser();
//        JSONObject jsonObject = (JSONObject)  parser.parse(res.getBody().toString());
//        JSONArray jsonArray1 = (JSONArray) jsonObject.get("fileInfo");
//
//        List<String> path  = new ArrayList<>();
//
//        for(int i = 0 ; i< jsonArray1.size();i++){
//            JSONObject obj = (JSONObject)  jsonArray1.get(i);
//            path.add(((String) obj.get("path")).replace("uploads\\",""));
//        }
//
//        System.out.println(path);







        return res;
    }



}
