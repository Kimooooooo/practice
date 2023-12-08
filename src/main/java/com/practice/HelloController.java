package com.practice;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("api/hello")
    public String hi(){
        log.info("홈 컨트롤러 ");
        return "홈컨트롤러";
    }
}
