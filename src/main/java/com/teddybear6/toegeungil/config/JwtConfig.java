package com.teddybear6.toegeungil.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.teddybear6.toegeungil.auth.dto.AuthUserDetail;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtConfig {

    private static final long ACCESS_TOKEN_ECPIRE_TIME = 1000 * 60 * 240; //30분 설정

    public String createToken(AuthUserDetail authUserDetail, String key){
        String jwtToken = JWT.create()
                .withSubject("tokenName")
                .withExpiresAt(new Date(System.currentTimeMillis()+ACCESS_TOKEN_ECPIRE_TIME))
                .withClaim("no", authUserDetail.getUserEntity().getUserNo())
                .withClaim("name", authUserDetail.getUserEntity().getUserName())
                .sign(Algorithm.HMAC512(key));

        return jwtToken;
    }

}
