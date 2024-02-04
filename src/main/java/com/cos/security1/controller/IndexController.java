package com.cos.security1.controller;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // View 를 리턴하겠다는 뜻
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/join")
    public String join(User user) {
        System.out.println(user);
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();  // 회원가입이 잘 되지만, 비밀번호가 그대로 암호화처리 없이 들어가버림 ( 시큐리티 로그인 불가능 )
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return "redirect:/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }
    
}
