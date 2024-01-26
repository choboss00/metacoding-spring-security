package com.cos.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // View 를 리턴하겠다는 뜻
public class IndexController {
    @GetMapping({"","/"})
    public String index(){
        return "index";
        // 원래 src/main/resources/templates/index.mustache 의 경로를 찾으러 감
        // 그래서 WebMvcConfig.java 파일을 통해 수정
        // mustache 기본 폴더는 src/main/resources/ 로 잡히기 때문에,
        // viewResolver 를 설정할 때는 templates(prefix), .mustache(suffix) 에 맞춰서 설정
    }

    @GetMapping("/user")
    public @ResponseBody String user() {
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "manager";
    }
    // 스프링 시큐리티 필터에서 /login 을 낚아채서 대신 로그인 페이지를 보여줌
    // SecurityConfig 파일 생성 후, spring security 에 존재하는 /login 이 작동하지 않게 됨
    @GetMapping("/login")
    public @ResponseBody String login() {
        return "login";
    }

    @GetMapping("/join")
    public @ResponseBody String join() {
        return "join";
    }

    @GetMapping("/joinProc")
    public @ResponseBody String joinProc() {
        return "회원가입 완료";
    }
    
}
