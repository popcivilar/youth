package com.popcivilar.youth.general.user.service.impl;

import com.popcivilar.youth.general.user.dao.UserInfoMapper;
import com.popcivilar.youth.general.user.entity.UserInfo;
import com.popcivilar.youth.general.user.service.UserInfoService;
import com.popcivilar.youth.youthbase.base.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;


    public String sayHello() {
        return userInfoMapper.selectByPrimaryKey("1").toString();
    }

}
