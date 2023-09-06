package com.teddybear6.toegeungil.config.filter;

import javax.servlet.*;
import java.io.IOException;

public class FirstFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        System.out.println("FirstFilter 실행");
        chain.doFilter(request, response);
    }

}
