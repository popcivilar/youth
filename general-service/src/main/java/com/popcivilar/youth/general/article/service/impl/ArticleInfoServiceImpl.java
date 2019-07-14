package com.popcivilar.youth.general.article.service.impl;

import com.popcivilar.youth.general.article.dao.ArticleInfoMapper;
import com.popcivilar.youth.general.article.entity.ArticleInfo;
import com.popcivilar.youth.general.article.service.ArticleInfoService;
import com.popcivilar.youth.youthbase.base.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleInfoServiceImpl extends BaseServiceImpl<ArticleInfo> implements ArticleInfoService {

    @Autowired
    private ArticleInfoMapper articleInfoMapper;
}
