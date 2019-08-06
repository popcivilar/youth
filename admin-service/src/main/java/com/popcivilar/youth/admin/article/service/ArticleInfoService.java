package com.popcivilar.youth.admin.article.service;

import com.popcivilar.youth.admin.article.entity.ArticleInfo;
import com.popcivilar.youth.admin.article.entity.ArticleInfoDto;
import com.popcivilar.youth.youthbase.base.entity.UniPage;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import com.popcivilar.youth.youthbase.base.service.BaseService;

public interface ArticleInfoService extends BaseService<ArticleInfo> {


    UniPage listByParam(UniParam<ArticleInfoDto> uniParam);
}
