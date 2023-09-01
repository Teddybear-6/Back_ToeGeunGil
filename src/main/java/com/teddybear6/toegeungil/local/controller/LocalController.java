package com.teddybear6.toegeungil.local.controller;

import com.teddybear6.toegeungil.category.dto.CategoryDTO;
import com.teddybear6.toegeungil.local.dto.LocalDTO;
import com.teddybear6.toegeungil.local.entity.Local;
import com.teddybear6.toegeungil.local.service.LocalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
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

    @GetMapping("/{localCode}")
    public ResponseEntity<Object> findLocalByCode(@PathVariable int localCode){
        Local local = localService.findById(localCode);

        if(Objects.isNull(local)){
            return ResponseEntity.status(404).body("존재하지 않는 지역입니다.");
        }
        LocalDTO localDTO = new LocalDTO(local);

        return ResponseEntity.ok().body(localDTO);
    }


    @PutMapping("/{localCode}")
    public ResponseEntity<?>  updateLocal(@PathVariable int localCode, @RequestBody LocalDTO localDTO){
        Local local = localService.findById(localCode);

        if(Objects.isNull(local)){
            return ResponseEntity.status(404).body("존재하지 않는 지역입니다.");

        }

         int result = localService.update(local,localDTO);

        if(result>0){
            return ResponseEntity.ok().body("수정 되었습니다.");
        }else {
            return ResponseEntity.status(500).body("수정되지 않았습니다.");
        }
    }


    @DeleteMapping("/{localCode}")
    public ResponseEntity<?> deleteLocal(@PathVariable int localCode){

        Local local = localService.findById(localCode);
        if(Objects.isNull(local)){
            return ResponseEntity.status(404).body("존재하지 않는 카테고리입니다.");
        }
        int result = localService.deleteLocal(localCode);


        if(result>0){
            return ResponseEntity.ok().body("삭제 성공했습니다.");
        }else{
            return ResponseEntity.status(500).body("삭제에 실패했습니다");
        }
    }

}
