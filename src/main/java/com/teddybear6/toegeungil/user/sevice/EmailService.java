package com.teddybear6.toegeungil.user.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
public class EmailService {
    @Autowired
    private  final JavaMailSender mailSender;

    private  final SpringTemplateEngine templateEngine;
    // 타임리프를사용하기 위한 객체를 의존성 주입으로 가져온다

    private String autnNum;

    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }


    public void createCode(){
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0:
                    key.append((char)((int)random.nextInt(26)+97));
                    break;
                case 1:
                    key.append((char) ((int) random.nextInt(26) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(9));
                    break;
            }
        }
        autnNum = key.toString();
    }

    //메일양식
    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {
//        MessagingException 메일 관련 exception
        createCode();
        String setFrom = "algoreatt@gmail.com";
        String toEmail = email;
        String title = "algore 회원가입 인증번호";

        MimeMessage message = mailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO,email); // 보낼 이메일
        message.setSubject(title); // 제목 설정
        message.setFrom(setFrom); // 보내는 이메일;
        message.setText(serContext(autnNum),"utf-8","html");
        return message;
    }

    //실제 메일 발송
    public String sendEmail(String toEmail) throws MessagingException,UnsupportedEncodingException{
        System.out.println("호출 서비스");

        MimeMessage eemailForm = createEmailForm(toEmail);

        //메일 전송
        mailSender.send(eemailForm);
        return autnNum;
    }
    public String serContext(String code) {
        Context context = new Context();
        context.setVariable("code",code);
        return templateEngine.process("common/mail",context);



    }

}
