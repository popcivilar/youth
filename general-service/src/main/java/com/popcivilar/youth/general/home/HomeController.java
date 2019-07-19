package com.popcivilar.youth.general.home;

import com.popcivilar.youth.youthbase.token.TokenPass;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName HomeController
 * @Description 首页
 * @Author zhangch
 * @Date 2019/7/15 15:51
 * @Version 1.0
 **/
@Controller
public class HomeController {

    @TokenPass
    @GetMapping("/")
    public String index(){
        return "index";
    }

}
