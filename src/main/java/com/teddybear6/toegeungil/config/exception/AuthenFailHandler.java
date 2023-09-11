package com.teddybear6.toegeungil.config.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.rmi.ServerException;

@Configuration
public class AuthenFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String errorMessage;
        if(exception instanceof BadCredentialsException){
            errorMessage ="아이디또는 비밀번호가 맞지 않습니다";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            errorMessage="서버에서 오류가 발생하였습니다. \n 관리자에게 문의해 주세요";
        } else if (exception instanceof UsernameNotFoundException) {
            errorMessage="존대하지 않는 이메일입니다.\n 이메일을 확인해 주세요";
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            errorMessage="인증요청이 거부되었습니다. \n 관리자에게 문의 해 주세요";
        } else{
            errorMessage="알수 없는 오류로 로그인 요청을 처리 할 수 없습니다 . \n 관리자에게 문의해 주세요";
        }

        errorMessage= URLEncoder.encode(errorMessage, "UTF-8");
        setDefaultFailureUrl("/v1/loginfail?errorMessage="+errorMessage);
        super.onAuthenticationFailure(request, response, exception);
    }
}
