package com.popcivilar.youth.admin.article.dao;

import com.popcivilar.youth.admin.article.entity.ArticleInfo;
import com.popcivilar.youth.admin.article.entity.ArticleInfoDto;
import com.popcivilar.youth.youthbase.base.entity.UniPage;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import com.popcivilar.youth.youthbase.base.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleInfoMapper extends BaseMapper<ArticleInfo> {

    List<ArticleInfoDto> listByParam(@Param("param") UniParam<ArticleInfoDto> param);
}
