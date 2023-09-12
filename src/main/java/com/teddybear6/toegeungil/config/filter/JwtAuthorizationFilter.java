package com.teddybear6.toegeungil.config.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.teddybear6.toegeungil.authjwt.auth.command.application.controller.AuthController;
import com.teddybear6.toegeungil.authjwt.auth.command.application.dto.AuthUserDetail;
import com.teddybear6.toegeungil.authjwt.user.command.domain.model.UserEntity;
import com.teddybear6.toegeungil.config.AuthentitationManager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final String AUTH_KEY;

    private final AuthController authController;

    public JwtAuthorizationFilter(AuthenticationManager authentitationManager, String AUTH_KEY, AuthController authController){
        super(authentitationManager);
        this.AUTH_KEY=AUTH_KEY;
        this.authController=authController;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtHeater = request.getHeader("Authorization");

        if(jwtHeater == null || !jwtHeater.startsWith("Bearer")){
            chain.doFilter(request,response);
            return;
        }
        String jwtToken = request.getHeader("Authorization").replace("Bearer","");
        String userId = JWT.require(Algorithm.HMAC512(AUTH_KEY)).build().verify(jwtToken).getClaim("userId").asString();

        if(!userId.isEmpty()){
            UserEntity user = authController.findUserEmail(userId);
            AuthUserDetail authUserDetail = new AuthUserDetail(user);

            Authentication authentication = new UsernamePasswordAuthenticationToken(authUserDetail, null, authUserDetail.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        System.out.println("context holder : ____" + SecurityContextHolder.getContext().getAuthentication());
        chain.doFilter(request, response);
    }
}
