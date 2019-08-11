package com.popcivilar.youth.general.article.service;

import com.popcivilar.youth.general.article.entity.ArticleInfo;
import com.popcivilar.youth.general.article.entity.ArticleInfoDto;
import com.popcivilar.youth.youthbase.base.entity.UniPage;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import com.popcivilar.youth.youthbase.base.service.BaseService;

public interface ArticleInfoService extends BaseService<ArticleInfo> {

    UniPage listView(UniParam<ArticleInfoDto> uniParam);

    ArticleInfoDto preNextInfo(ArticleInfoDto articleInfoDto);
}
