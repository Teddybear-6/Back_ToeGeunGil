package com.teddybear6.toegeungil.config;

import com.teddybear6.toegeungil.auth.controller.AuthController;
import com.teddybear6.toegeungil.auth.vo.UserRole;
import com.teddybear6.toegeungil.config.exception.AuthenFailHandler;
import com.teddybear6.toegeungil.config.filter.JwtAuthenticationFilter;
import com.teddybear6.toegeungil.config.filter.JwtAuthorizationFilter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    private final JwtConfig jwtConfig;

    private final AuthController authController;

    private static String AUTH_KEY;

    private final AuthenFailHandler authenFailHandler;

    public SecurityConfig(JwtConfig jwtConfig, AuthController authController,
                          @Value("${Jwt.key}")
                          String key,
                          AuthenFailHandler authenFailHandler) {
        this.jwtConfig = jwtConfig;
        this.authController =authController;
        this.AUTH_KEY=key;
        this.authenFailHandler =authenFailHandler;


    }



    @Bean
    public BCryptPasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder();}


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter())
                .formLogin().failureHandler(authenFailHandler).disable()
                .httpBasic().disable()

                .addFilter(new JwtAuthenticationFilter(authenticationManager(), AUTH_KEY, jwtConfig))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), AUTH_KEY, authController))
                .authorizeRequests()
                .antMatchers("/member/**")
                .hasAnyAuthority(UserRole.USER.getValue(), UserRole.ADMIN.getValue())
                .antMatchers("/admin/**")
                .hasAnyAuthority(UserRole.ADMIN.getValue())
                .antMatchers("/**").permitAll()
                .anyRequest().permitAll();
    }

    @Bean
    CorsFilter corsFilter(){

        List<String> headers = new ArrayList<>();
        headers.add("Authorization");
        CorsConfiguration configuration= new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        configuration.setAllowCredentials(true);
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setExposedHeaders(headers);
        source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(source);


    }


}
