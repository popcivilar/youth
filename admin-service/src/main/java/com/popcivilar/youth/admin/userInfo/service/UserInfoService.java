package com.popcivilar.youth.admin.userInfo.service;


import com.popcivilar.youth.admin.userInfo.entity.UserInfo;
import com.popcivilar.youth.youthbase.base.service.BaseService;

public interface UserInfoService extends BaseService<UserInfo>{

    String sayHello();


    UserInfo login(UserInfo userInfo);
}
