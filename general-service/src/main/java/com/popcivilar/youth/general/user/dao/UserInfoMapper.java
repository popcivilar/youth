package com.popcivilar.youth.general.user.dao;


import com.popcivilar.youth.general.user.entity.UserInfo;
import com.popcivilar.youth.youthbase.base.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}