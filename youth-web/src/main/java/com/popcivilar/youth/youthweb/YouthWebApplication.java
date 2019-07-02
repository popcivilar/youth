package com.popcivilar.youth.youthweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan
public class YouthWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouthWebApplication.class, args);
    }

}
