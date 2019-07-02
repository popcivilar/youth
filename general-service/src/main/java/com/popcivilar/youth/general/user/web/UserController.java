package com.popcivilar.youth.general.user.web;


import com.popcivilar.youth.general.user.service.UserInfoService;
import com.popcivilar.youth.youthbase.base.ModuleReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getInfo")
    public ModuleReturn getInfo(){
        String msg = userInfoService.sayHello();
        ModuleReturn<String> success = ModuleReturn.success(msg);
        return success;
    }

}
