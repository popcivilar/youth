package com.popcivilar.youth.admin.article.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.popcivilar.youth.admin.article.dao.ArticleInfoMapper;
import com.popcivilar.youth.admin.article.entity.ArticleInfo;
import com.popcivilar.youth.admin.article.entity.ArticleInfoDto;
import com.popcivilar.youth.admin.article.service.ArticleInfoService;
import com.popcivilar.youth.youthbase.base.entity.UniPage;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import com.popcivilar.youth.youthbase.base.service.BaseServiceImpl;
import com.popcivilar.youth.youthbase.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArticleInfoServiceImpl extends BaseServiceImpl<ArticleInfo> implements ArticleInfoService {

    @Autowired
    private ArticleInfoMapper articleInfoMapper;


    @Override
    public UniPage listByParam(UniParam<ArticleInfoDto> uniParam) {
        Page page = PageHelper.startPage(uniParam.getPage(), uniParam.getPageSize());
        articleInfoMapper.listByParam(uniParam);
        return new UniPage(page);
    }
}
