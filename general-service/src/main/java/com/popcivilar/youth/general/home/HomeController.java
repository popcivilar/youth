package com.popcivilar.youth.general.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName HomeController
 * @Description 首页
 * @Author zhangch
 * @Date 2019/7/15 15:51
 * @Version 1.0
 **/
@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
