package com.cos.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // csrf 비활성화
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() // 인증 필요
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") // 인증 필요
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // 인증 필요
                .anyRequest().permitAll() // 나머지는 권한 필요 없음
                .and()
                .formLogin()
                .loginPage("/login"); // 스프링 시큐리티가 해당 주소로 요청이 오는 로그인을 가로채서 대신 로그인 페이지를 보여줌

        return http.build();

    }

}
