package com.teddybear6.toegeungil.user.controller;

import com.teddybear6.toegeungil.auth.dto.LoginReqDTO;
import com.teddybear6.toegeungil.user.dto.InsertUserDTO;
import com.teddybear6.toegeungil.user.entity.UserEntity;
import com.teddybear6.toegeungil.user.sevice.UserViewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserViewService userViewService;

    public UserController(UserViewService userViewService) {
        this.userViewService = userViewService;
    }

    public UserEntity findUserEmail(String userId) {
        UserEntity user = userViewService.findUserEmail(userId);
        return user;
    }

    @PostMapping("/regist")
    public ResponseEntity<?> regist(@RequestBody InsertUserDTO user) {
        int result = userViewService.registUser(user);
        if (result > 0) {
            return ResponseEntity.ok().body("Success");
        }
        return ResponseEntity.status(500).body("실패했습니다.");

    }


    @GetMapping("/{userNo}")
    public ResponseEntity<?> findUser(@PathVariable int userNo) {
        UserEntity result = userViewService.findById(userNo);

        if (Objects.isNull(result)) {
           return ResponseEntity.status(404).body(null);
        }else {
            LoginReqDTO loginReqDTO = new LoginReqDTO();
            loginReqDTO.setEmail(result.getUserEmail());
            loginReqDTO.setUserName(result.getUserName());
            loginReqDTO.setUserNo(result.getUserNo());
            loginReqDTO.setUserRole(result.getRole());
            loginReqDTO.setNickName(result.getNickName());
            return ResponseEntity.ok().body(loginReqDTO);
        }
    }


//    @PutMapping("/update")
//    public ResponseEntity<?> update(@RequestBody UserEntity user) {
//        UserEntity existingUser = userViewService.findUserEmail(userViewService.findUserEmail());
//        if (Objects.isNull(existingUser)) {
//            return ResponseEntity.ok().body("데이터가 존재하지 않습니다");
//        }
//        int result = userViewService.equals(findUserEmail, user);
//        if (result > 0) {
//            return ResponseEntity.ok().body("수정완료");
//        } else {
//            return ResponseEntity.status(400).body("수정실패");
//        }
//    }
//
//    @DeleteMapping("/{delete}")
//    public ResponseEntity<?> delete(@PathVariable int delete) {
//        userViewService.deleteCode(delete);
//        return ResponseEntity.ok().body("삭제완료");
//    }


}
