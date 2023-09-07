package com.teddybear6.toegeungil.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teddybear6.toegeungil.auth.dto.AuthUserDetail;
import com.teddybear6.toegeungil.auth.dto.LoginDTO;
import com.teddybear6.toegeungil.auth.dto.LoginReqDTO;
import com.teddybear6.toegeungil.config.JwtConfig;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    private String key;

    private JwtConfig jwtConfig;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, String key, JwtConfig jwtConfig){
        this.authenticationManager = authenticationManager;
        this.key = key;
        this.jwtConfig = jwtConfig;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {


        try {
            ObjectMapper ObjLogin = new ObjectMapper();
            LoginDTO loginDto = ObjLogin.readValue(request.getInputStream(), LoginDTO.class);

            // 사용자 입력 정보를 받아 토큰을 생성해준다.
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getUserPass());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            return authentication;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
        @Override
        protected void successfulAuthentication(HttpServletRequest request,
                HttpServletResponse response,
                FilterChain chain,
                Authentication authResult) throws IOException, ServletException {

            AuthUserDetail authUserDetail = (AuthUserDetail) authResult.getPrincipal();
            String jwtToken = jwtConfig.createToken(authUserDetail, key);

            LoginReqDTO loginReqDTO = new LoginReqDTO();
            loginReqDTO.setUserRole(authUserDetail.getUserEntity().getRole());
            loginReqDTO.setUserNo(Integer.parseInt(authUserDetail.getUserEntity().getUserNo()));
            loginReqDTO.setEmail(authUserDetail.getUserEntity().getUserEmail());
            loginReqDTO.setUserName(authUserDetail.getUserEntity().getUserName());

            ObjectMapper objectMapper = new ObjectMapper();
            String responseValue = objectMapper.writeValueAsString(loginReqDTO);


            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Authorization", "Bearer " + jwtToken);
            response.getWriter().println(responseValue);


        }
    }

