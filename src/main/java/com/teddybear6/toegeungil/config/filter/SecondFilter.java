package com.teddybear6.toegeungil.config.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecondFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        System.out.println("JWT filter 실행");

        if(req.getMethod().equals("POST")){
            String headerAuth = req.getHeader("Authorization");
            chain.doFilter(req,res);
        }else{
            chain.doFilter(request, response);
        }
        chain.doFilter(req, res);
    }
}
