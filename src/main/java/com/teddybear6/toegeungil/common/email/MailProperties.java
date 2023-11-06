package com.teddybear6.toegeungil.common.email;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
public class MailProperties {

    // SMTP 서버

    private static String host;

    // 계정

    private static String username;

    // 비밀번호

    private static String password;

    // 포트번호

    private  static int port;

    public MailProperties() {
    }

    public MailProperties(String host, String username, String password, int port) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    @Value("${email.host}")
    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    @Value("${email.username}")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @Value("${email.password}")
    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    @Value("${email.port}")
    public void setPort(int port) {
        this.port = port;
    }
}