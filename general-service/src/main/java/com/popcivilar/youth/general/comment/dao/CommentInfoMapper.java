package com.popcivilar.youth.general.comment.dao;

import com.popcivilar.youth.general.comment.entity.CommentInfo;
import com.popcivilar.youth.general.comment.entity.CommentInfoDto;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import com.popcivilar.youth.youthbase.base.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentInfoMapper extends BaseMapper<CommentInfo> {

    List<CommentInfoDto> listByParam(@Param("param") UniParam<CommentInfoDto> param);
}
