package com.cos.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
