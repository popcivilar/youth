package com.popcivilar.youth.general.user.web;


import com.popcivilar.youth.general.user.entity.UserInfo;
import com.popcivilar.youth.general.user.entity.UserInfoDto;
import com.popcivilar.youth.general.user.service.UserInfoService;
import com.popcivilar.youth.youthbase.base.controller.BaseController;
import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<UserInfo, UserInfoDto,UserInfoService> {


    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/getInfo")
    public ModuleReturn getInfo(){
        userInfoService.sayHello();
        UserInfo userInfo = userInfoService.selectByPrimaryKey("1");
        redisTemplate.opsForValue().set("user",userInfo);
        ModuleReturn<UserInfo> success = ModuleReturn.success(userInfo);
        return success;
    }

}
