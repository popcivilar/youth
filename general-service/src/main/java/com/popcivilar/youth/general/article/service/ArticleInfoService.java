package com.popcivilar.youth.general.article.service;

import com.popcivilar.youth.general.article.entity.ArticleInfo;
import com.popcivilar.youth.general.article.entity.ArticleInfoDto;
import com.popcivilar.youth.general.article.entity.ArticleView;
import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
import com.popcivilar.youth.youthbase.base.entity.UniPage;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import com.popcivilar.youth.youthbase.base.service.BaseService;

import java.util.List;

public interface ArticleInfoService extends BaseService<ArticleInfo> {

    UniPage listView(UniParam<ArticleInfoDto> uniParam);

    ArticleInfoDto preNextInfo(ArticleInfoDto articleInfoDto);

    void putArticleRankindInRedis(ArticleInfoDto articleInfoDto);

    List<ArticleInfoDto> getArticleRankindInRedis();

    Integer countArticle();

    List<ArticleView> queryLatest(int indexShowNum);

    List<ArticleView> queryByParam(ArticleInfoDto articleInfoDto);


    List<ArticleView> queryTopArticle();
}
