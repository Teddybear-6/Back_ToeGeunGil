package com.teddybear6.toegeungil.user.controller;

import com.teddybear6.toegeungil.auth.dto.LoginReqDTO;
import com.teddybear6.toegeungil.auth.vo.UserRole;
import com.teddybear6.toegeungil.common.email.MailProperties;
import com.teddybear6.toegeungil.user.dto.EmailAuthDTO;
import com.teddybear6.toegeungil.user.dto.FindPassDTO;
import com.teddybear6.toegeungil.user.dto.InsertUserDTO;
import com.teddybear6.toegeungil.user.entity.UserEntity;
import com.teddybear6.toegeungil.user.sevice.EmailService;
import com.teddybear6.toegeungil.user.sevice.UserViewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserViewService userViewService;
    private final EmailService emailService;
    private final MailProperties mailProperties;

    public UserController(UserViewService userViewService, EmailService emailService, MailProperties mailProperties) {
        this.userViewService = userViewService;
        this.emailService = emailService;
        this.mailProperties = mailProperties;
    }

    public UserEntity findUserEmail(String userId) {
        UserEntity user = userViewService.findUserEmail(userId);
        return user;
    }

    // 아이디랑 이름이랑 이메일 가지고 회원을 조회해서 맞는 회원이 있으면 비밀를 복호화 해서 담아서 보내줘요
    //1. 넘어오는 값으로 조회
    //2. 널이면 존재하지 않는 회원이라고 알려주고
    //3. 널이 아니면 비밀번호 복호화를 해서 담아서 리턴
    @PostMapping("/regist") // 회원가입
    public ResponseEntity<?> regist(@RequestBody InsertUserDTO user) {
        System.out.println(user);
        UserEntity userEntity = userViewService.findUserEmail(user.getUserEmail());

        if(!Objects.isNull(userEntity)){
            return ResponseEntity.ok().body("이미 있는 계정입니다.");
        }
        user.setRole(UserRole.USER.getValue());
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

    @PostMapping("/findEmail")
    public ResponseEntity<?> findEmail(@RequestBody String  email){
        System.out.println("email : " + email);
        UserEntity user = userViewService.findUserEmail(email);

        if(!Objects.isNull(user)){
            //이미 있음
            return ResponseEntity.ok().body("중복된 회원이 존재합니다.");
        }
        //없음
        return ResponseEntity.ok().body("사용가능한 아이디 입니다.");

    }

    /* 비밀번호 찾기
    * */
    @PostMapping("/findPass")
    //이름, 닉네임 이메일 
    // 
    public ResponseEntity<?> findpass(@RequestBody FindPassDTO passDTO /*이름 닉네임 이메일 받기*/){
         String pass = userViewService.finduserpass(passDTO /*이름 닉네임 이메일 넣기*/);

        System.out.println(pass);
//        if(!Objects.isNull(userViewService)){
//            return ResponseEntity.ok().body(false);
//        }
        return ResponseEntity.ok().body(true);
    }

//    @PostMapping("/sendtemppwd")
//    public boolean sendTemPwd(@RequestParam("email")String email, @RequestParam("randnum")String randnum){
//        if(userViewService.updateTempPwd(email,randnum)){
//            return emailservice.sendTempPwd(email,8);
//        }
//        return false;
//    }

//    @GetMapping()

//    @RestController
//    public class userController{
//        private final SignupService service;
//
//        public  userController(SignupService service){
//            this.service=service;
//        }
//
//        @GetMapping("/findEmail")
//        public String findEmail(@RequestParam("username") String userName){
//            System.out.println("넘어가가가가");
//            String findName = service.findName(userName);
//
//            if(findName==null){
//                System.out.println("1");
//                return "1";
//            }else {
//                System.out.println("2");
//                return "2";
//            }
//        }
//    }



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


    //이메일
    @ResponseBody
    @PostMapping("/mailConfirm")
    public String mailConfirm(@RequestBody EmailAuthDTO emailAuthDTO) throws MessagingException, UnsupportedEncodingException {
        System.out.println("호출");

        String authCode = emailService.sendEmail(emailAuthDTO.getEmail());

        System.out.println(authCode);
        return authCode;

    }

}
