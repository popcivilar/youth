package com.popcivilar.youth.general.type.service;

import com.popcivilar.youth.general.type.entity.ArticleTabNav;
import com.popcivilar.youth.general.type.entity.TypeInfo;
import com.popcivilar.youth.general.type.entity.TypeInfoDto;
import com.popcivilar.youth.youthbase.base.service.BaseService;

import java.util.List;

public interface TypeInfoService extends BaseService<TypeInfo> {


    List<TypeInfo> getTypeInfoCloudInRedis();

    List<ArticleTabNav> getArticleTabNav();
}
