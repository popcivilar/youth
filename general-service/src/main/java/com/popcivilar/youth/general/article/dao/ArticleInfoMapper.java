package com.popcivilar.youth.general.article.dao;

import com.popcivilar.youth.general.article.entity.ArticleInfo;
import com.popcivilar.youth.general.article.entity.ArticleInfoDto;
import com.popcivilar.youth.general.article.entity.ArticleView;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import com.popcivilar.youth.youthbase.base.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleInfoMapper extends BaseMapper<ArticleInfo> {

    List<ArticleView> listView(@Param("param") UniParam<ArticleInfoDto> uniParam);

    ArticleInfoDto preArticle(@Param("id") Integer id);

    ArticleInfoDto nextArticle(@Param("id") Integer id);

    List<ArticleView> queryLatest(@Param("indexShowNum") int indexShowNum);

    List<ArticleView> queryByParam(@Param("param") ArticleInfoDto articleInfoDto);
}
