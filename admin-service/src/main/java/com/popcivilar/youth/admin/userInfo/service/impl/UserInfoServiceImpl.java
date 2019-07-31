package com.popcivilar.youth.admin.userInfo.service.impl;

import com.popcivilar.youth.admin.userInfo.dao.UserInfoMapper;
import com.popcivilar.youth.admin.userInfo.entity.UserInfo;
import com.popcivilar.youth.admin.userInfo.service.UserInfoService;
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


    @Override
    public UserInfo login(UserInfo userInfo) {
        userInfo.setDeletedFlag("0");
        userInfo = userInfoMapper.selectOne(userInfo);
        return userInfo;
    }
}
