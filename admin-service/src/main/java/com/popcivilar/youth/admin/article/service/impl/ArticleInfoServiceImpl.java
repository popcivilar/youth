package com.popcivilar.youth.admin.article.service.impl;

import com.popcivilar.youth.admin.article.dao.ArticleInfoMapper;
import com.popcivilar.youth.admin.article.entity.ArticleInfo;
import com.popcivilar.youth.admin.article.service.ArticleInfoService;
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
