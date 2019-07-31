package com.popcivilar.youth.admin.userInfo.web;


import com.popcivilar.youth.admin.userInfo.entity.UserInfo;
import com.popcivilar.youth.admin.userInfo.entity.UserInfoDto;
import com.popcivilar.youth.admin.userInfo.service.UserInfoService;
import com.popcivilar.youth.youthbase.base.controller.BaseController;
import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
import com.popcivilar.youth.youthbase.token.TokenPass;
import com.popcivilar.youth.youthbase.token.TokenUtil;
import com.popcivilar.youth.youthbase.utils.ResUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 *
 */
@RestController
@RequestMapping("/userInfo")
public class UserController extends BaseController<UserInfo, UserInfoDto,UserInfoService> {


    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/getInfo")
    public ModuleReturn getInfo() {
        userInfoService.sayHello();
        UserInfo userInfo = userInfoService.selectByPrimaryKey("1");
        redisTemplate.opsForValue().set("userInfo", userInfo);
        ModuleReturn<UserInfo> success = ModuleReturn.success(userInfo);
        return success;
    }


    /**
     * 获取Session中登录用户
     *
     * @param request
     * @return
     */
    @GetMapping("/loadLoginUser")
    public ModuleReturn<UserInfo> loadLoginUser(HttpServletRequest request) {
        ModuleReturn<UserInfo> moduleReturn = ModuleReturn.success();
        UserInfo loginUser = (UserInfo) request.getSession().getAttribute("loginUser");
        moduleReturn.setData(loginUser);
        return moduleReturn;
    }


    @RequestMapping(value = "updatePwd",method = RequestMethod.POST)
    public ModuleReturn<UserInfoDto> updatePwd(UserInfoDto userInfoDto) {
        ModuleReturn<UserInfoDto> moduleReturn = ModuleReturn.success();
        UserInfo userInfo = new UserInfo();
        userInfo.setDeletedFlag("0");
        userInfo.setUserCode(userInfoDto.getUserCode());
        userInfo = userInfoService.selectOne(userInfo);
        if (userInfo == null || userInfo.getId() == null) {
            moduleReturn.setCode(ResUtils.ERROR);
            moduleReturn.setReturnMsg("用户不存在");
            return moduleReturn;
        }
        if(!userInfo.getUserPwd().equals(userInfoDto.getOldPassword())){
            moduleReturn.setCode(ResUtils.ERROR);
            moduleReturn.setReturnMsg("密码验证失败");
            return moduleReturn;
        }
        userInfo.setUserPwd(userInfoDto.getUserPwd());
        int updateNum = userInfoService.updateByPrimaryKeySelective(userInfo);
        if(updateNum <= 0){
            moduleReturn.setCode(ResUtils.ERROR);
            moduleReturn.setReturnMsg("用户更新失败");
            return moduleReturn;
        }
        return moduleReturn;
    }

    /**
     * 登录
     * @param userInfo
     * @return  token
     */
    @TokenPass
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ModuleReturn<UserInfoDto> login(UserInfo userInfo, HttpServletRequest request) {
        ModuleReturn<UserInfoDto> moduleReturn = ModuleReturn.success();
        //1.校验登陆
        UserInfo loginUser = userInfoService.login(userInfo);
        if(loginUser == null){
            moduleReturn.setCode(ResUtils.USER_CODE_OR_PWD_ERROR);//登录失败
            moduleReturn.setReturnMsg("用户名或密码错误");
            return moduleReturn;
        }
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(loginUser,userInfoDto);
        //2.获取Token
        userInfoDto.setToken(TokenUtil.createJwtToken(loginUser.getUserCode()));
        //3.loginUser存储到session
        HttpSession session = request.getSession();
        session.setAttribute("loginUser",loginUser);
        //4.密码置空
        userInfoDto.setUserPwd(null);
        moduleReturn.setData(userInfoDto);
        return moduleReturn;
    }
}