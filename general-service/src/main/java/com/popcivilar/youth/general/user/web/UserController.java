package com.popcivilar.youth.general.user.web;


import com.popcivilar.youth.general.user.entity.UserInfo;
import com.popcivilar.youth.general.user.service.UserInfoService;
import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
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
        userInfoService.sayHello();
        UserInfo userInfo = userInfoService.selectByPrimaryKey("1");
        ModuleReturn<UserInfo> success = ModuleReturn.success(userInfo);
        return success;
    }

}
