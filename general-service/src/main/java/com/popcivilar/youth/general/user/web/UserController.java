package com.popcivilar.youth.general.user.web;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.popcivilar.youth.general.user.entity.UserInfo;
import com.popcivilar.youth.general.user.entity.UserInfoDto;
import com.popcivilar.youth.general.user.service.UserInfoService;
import com.popcivilar.youth.youthbase.base.controller.BaseController;
import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
import com.popcivilar.youth.youthbase.token.TokenUtil;
import com.popcivilar.youth.youthbase.utils.ResUtils;
import com.popcivilar.youth.youthbase.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getInfo")
    public ModuleReturn getInfo(){
        userInfoService.sayHello();
        UserInfo userInfo = userInfoService.selectByPrimaryKey("1");
        redisTemplate.opsForValue().set("userInfo",userInfo);
        ModuleReturn<UserInfo> success = ModuleReturn.success(userInfo);
        return success;
    }


    /**
     * 登录
     * @param userInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ModuleReturn<UserInfoDto> login(UserInfo userInfo, HttpServletRequest request) {
        ModuleReturn<UserInfoDto> moduleReturn = ModuleReturn.success();
        //1.校验登陆
        if(userInfo.getUserPwd() == null || userInfo.getUserCode() == null){
            moduleReturn.setCode(ResUtils.ERROR);
            moduleReturn.setReturnMsg("请填写用户名或密码");
            return moduleReturn;
        }
        //2.调用admin服务登录
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("userCode",userInfo.getUserCode());
        params.add("userPwd",userInfo.getUserPwd());


        String url = "http://localhost:8090/userInfo/login";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity httpEntity = new HttpEntity(params,null);
        ResponseEntity<String> returnResult = restTemplate.postForEntity(url, httpEntity,String.class);
        if (ResUtils.SUCCESS.equals(StringUtil.objToString(returnResult.getStatusCodeValue()))) {//接口请求成功
            String body = returnResult.getBody();
            System.out.println(body);
            moduleReturn = JSON.parseObject(body, ModuleReturn.class);
        }else{
            moduleReturn.setCode(ResUtils.ERROR);
            moduleReturn.setReturnMsg("接口异常");
        }

        return moduleReturn;
    }

}
