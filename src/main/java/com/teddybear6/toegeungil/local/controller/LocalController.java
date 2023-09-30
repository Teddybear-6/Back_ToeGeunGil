package com.teddybear6.toegeungil.local.controller;

import com.teddybear6.toegeungil.auth.dto.AuthUserDetail;
import com.teddybear6.toegeungil.category.dto.CategoryDTO;
import com.teddybear6.toegeungil.local.dto.LocalDTO;
import com.teddybear6.toegeungil.local.entity.Local;
import com.teddybear6.toegeungil.local.service.LocalService;
import com.teddybear6.toegeungil.user.entity.UserEntity;
import com.teddybear6.toegeungil.user.sevice.UserViewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/local")
@Api(value = "지역 Api", tags = {"11. Local Info"}, description = "지역 Api")
@ApiResponses({
        @ApiResponse(code = 200,message = "성공"),
        @ApiResponse(code = 404,message = "잘못된 접근") ,
        @ApiResponse(code = 500,message = "서버에러")
})
public class LocalController {

    private final LocalService  localService;
    private final UserViewService userViewService;

    public LocalController(LocalService localService, UserViewService userViewService) {
        this.localService = localService;
        this.userViewService = userViewService;
    }

    @GetMapping //전체 조회
    @ApiOperation(value = "지역 전체 조회 Api", notes = "지역 전체 목록을 조회한다.")
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

    @PostMapping //지역 등록
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "지역 등록 Api", notes = "새로운 지역을 등록한다.")
    public ResponseEntity<?> registLocal(@AuthenticationPrincipal AuthUserDetail userDetails,@RequestBody LocalDTO localDTO){
        System.out.println(userDetails);
        UserEntity userEntity = userViewService.findUserEmail(userDetails.getUserEntity().getUserEmail());
        Map<String, String> respose = new HashMap<>();

        if (Objects.isNull(userEntity)) {
            respose.put("value", "관리자가 아닙니다.");
            return ResponseEntity.status(500).body(respose);
        }

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

    @GetMapping("/{localCode}") //단일 조회
    @ApiOperation(value = "지역 단일 조회 Api", notes = "지역 번호로 해당 지역 목록을 조회한다.")
    public ResponseEntity<Object> findLocalByCode(@PathVariable int localCode){

        Local local = localService.findById(localCode);

        if(Objects.isNull(local)){
            return ResponseEntity.status(404).body("존재하지 않는 지역입니다.");
        }
        LocalDTO localDTO = new LocalDTO(local);

        return ResponseEntity.ok().body(localDTO);
    }


    @PutMapping("/{localCode}") //지역 수정
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "지역 수정 Api", notes = "지역 번호로 해당 지역 정보를 수정한다.")
    public ResponseEntity<?>  updateLocal(@AuthenticationPrincipal AuthUserDetail userDetails,@PathVariable int localCode, @RequestBody LocalDTO localDTO){
        System.out.println(userDetails);
        UserEntity userEntity = userViewService.findUserEmail(userDetails.getUserEntity().getUserEmail());
        Map<String, String> respose = new HashMap<>();

        if (Objects.isNull(userEntity)) {
            respose.put("value", "관리자가 아닙니다.");
            return ResponseEntity.status(500).body(respose);
        }

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


    @DeleteMapping("/{localCode}") //지역 삭제
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "카테고리 삭제 Api", notes = "지역 번호로 해당 지역 정보를 삭제한다.")
    public ResponseEntity<?> deleteLocal(@AuthenticationPrincipal AuthUserDetail userDetails,@PathVariable int localCode){
        System.out.println(userDetails);
        UserEntity userEntity = userViewService.findUserEmail(userDetails.getUserEntity().getUserEmail());
        Map<String, String> respose = new HashMap<>();

        if (Objects.isNull(userEntity)) {
            respose.put("value", "관리자가 아닙니다.");
            return ResponseEntity.status(500).body(respose);
        }

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
