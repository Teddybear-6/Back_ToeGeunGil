package com.teddybear6.toegeungil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ToeGeunGilApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToeGeunGilApplication.class, args);
    }

}
