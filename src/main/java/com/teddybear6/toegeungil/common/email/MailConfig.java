package com.teddybear6.toegeungil.common.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Configuration
@Component

public class MailConfig {
    Properties pt = new Properties();

    private final MailProperties mailProperties;



    public MailConfig(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }

    @Bean
    public JavaMailSender javaMailService() {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(this.mailProperties.getHost());
        javaMailSender.setUsername(this.mailProperties.getUsername());

        javaMailSender.setPassword(this.mailProperties.getPassword());
        javaMailSender.setPort(this.mailProperties.getPort());

        pt.put("mail.smtp.auth", true);
        pt.put("mail.smtp.starttls.enable", true);
        pt.put("mail.smtp.starttls.required", true);
        pt.put("mail.transport.protocol", "smtp");
        javaMailSender.setJavaMailProperties(pt);
        javaMailSender.setDefaultEncoding("UTF-8");

        return javaMailSender;


    }
}