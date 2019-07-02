package com.popcivilar.youth.general;

import com.popcivilar.youth.youthbase.config.FastJsonConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeneralServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(GeneralServiceApplication.class, args);
    }

}
