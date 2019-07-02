package com.popcivilar.youth.youthweb.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestDemo {

    @GetMapping("/page")
    public String page(){
        return "yes";
    }
}
