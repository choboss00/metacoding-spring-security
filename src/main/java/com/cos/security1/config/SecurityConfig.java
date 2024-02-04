package com.cos.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화, preAuthorize 어노테이션 활성화
public class SecurityConfig {

    // 해당 method 의 return 되는 오브젝트를 ioc 로 등록해줌
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

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
                .loginPage("/loginForm") // 스프링 시큐리티가 해당 주소로 요청이 오는 로그인을 가로채서 대신 로그인 페이지를 보여줌
                .loginProcessingUrl("/login") // login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해 줍니다.
                .defaultSuccessUrl("/"); // 로그인이 성공하면 해당 주소로 이동
        return http.build();

    }

}
