package com.popcivilar.youth.admin.userInfo.dao;


import com.popcivilar.youth.admin.userInfo.entity.UserInfo;
import com.popcivilar.youth.youthbase.base.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}