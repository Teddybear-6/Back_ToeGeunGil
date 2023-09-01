package com.teddybear6.toegeungil.local.controller;

import com.teddybear6.toegeungil.category.dto.CategoryDTO;
import com.teddybear6.toegeungil.local.dto.LocalDTO;
import com.teddybear6.toegeungil.local.entity.Local;
import com.teddybear6.toegeungil.local.service.LocalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/local")
public class LocalController {

    private final LocalService  localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @GetMapping
    public ResponseEntity<List<?>> findAllLocal(){
        List<Local> localList = localService.findAll();

        if(localList.size()==0){
            List<String> error = new ArrayList<>();
            error.add("지역이 존재하지 않습니다.");
            return ResponseEntity.status(500).body(error);
        }
        List<LocalDTO> localDTOS = localList.stream().map(m-> new LocalDTO(m)).collect(Collectors.toList());

        return ResponseEntity.ok().body(localList);


    }

    @PostMapping
    public ResponseEntity<?> registLocal(LocalDTO localDTO){

        Local local = new Local(localDTO);
        List<Local> findlocal = localService.findByName(localDTO.getLocalName());
        if(findlocal.size()>0){
            return ResponseEntity.status(404).body("중복되는 지역이 존재합니다");
        }
        System.out.println(findlocal);
        int result = localService.registLocal(local);

        if(result>0){
            return ResponseEntity.ok().body("등록 성공했습니다.");
        }else {
            return ResponseEntity.status(500).body("등록에 실패했습니다.");
        }




    }



}
