package com.popcivilar.youth.general.article.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.popcivilar.youth.general.article.dao.ArticleInfoMapper;
import com.popcivilar.youth.general.article.entity.ArticleInfo;
import com.popcivilar.youth.general.article.entity.ArticleInfoDto;
import com.popcivilar.youth.general.article.service.ArticleInfoService;
import com.popcivilar.youth.youthbase.base.entity.UniPage;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import com.popcivilar.youth.youthbase.base.service.BaseServiceImpl;
import com.popcivilar.youth.youthbase.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ArticleInfoServiceImpl extends BaseServiceImpl<ArticleInfo> implements ArticleInfoService {

    private static Logger logger = LoggerFactory.getLogger(ArticleInfoServiceImpl.class);

    @Autowired
    private ArticleInfoMapper articleInfoMapper;


    private RedisTemplate redisTemplate;

    private static final String REDIS_PRE_NEXT_ARTICLE_KEY = "REDIS_PRE_NEXT_ARTICLE_KEY";//上一篇。下一篇

    @Value("${youth.redis.cache.time:3600}")
    private int expire;//过期时间


    @Value("${spring.application.name}")
    private String sysTag;//系统标识

    @Autowired
    public void initRedisTemplate(RedisTemplate redisTemplate) {
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        this.redisTemplate = redisTemplate;
    }

    public BoundHashOperations getOper() {
        return redisTemplate.boundHashOps(REDIS_PRE_NEXT_ARTICLE_KEY);
    }

    @Override
    public UniPage listView(UniParam<ArticleInfoDto> uniParam) {
        Page page = PageHelper.startPage(uniParam.getPage(), uniParam.getPageSize());
        articleInfoMapper.listView(uniParam);
        return new UniPage(page);
    }


    /**
     * 绑定值
     * @param boKey
     * @return
     */
    public String getKey(String boKey) {
        return sysTag + "_" + boKey;
    }


    @Override
    public ArticleInfoDto preNextInfo(ArticleInfoDto articleInfoDto) {

        BoundHashOperations bho = getOper();
        String key = getKey(StringUtil.objToString(articleInfoDto.getId()));
        if (bho.hasKey(key)) {
            logger.debug("从Redis 获取【{}】代码表", key);
            List<ArticleInfoDto> preNextArticleList = (List<ArticleInfoDto>) bho.get(key);
            if (preNextArticleList != null && preNextArticleList.size() > 0) {
                for (ArticleInfoDto infoDto : preNextArticleList) {
                    if(infoDto.getId() < articleInfoDto.getId()){
                        articleInfoDto.setPreArticle(infoDto);
                    }else if(infoDto.getId() > articleInfoDto.getId()){
                        articleInfoDto.setNextArticle(infoDto);
                    }
                }
            }
            return articleInfoDto;
        }
        //从数据库中获取
        List<ArticleInfoDto> preNextArticleList = new ArrayList<>();
        ArticleInfoDto preArticleInfoDto = articleInfoMapper.preArticle(articleInfoDto.getId());
        ArticleInfoDto nextArticleInfoDto = articleInfoMapper.nextArticle(articleInfoDto.getId());
        if(preArticleInfoDto != null && preArticleInfoDto.getId() != null){
            articleInfoDto.setPreArticle(preArticleInfoDto);
            preNextArticleList.add(preArticleInfoDto);
        }
        if(nextArticleInfoDto != null && nextArticleInfoDto.getId() != null){
            articleInfoDto.setNextArticle(nextArticleInfoDto);
            preNextArticleList.add(nextArticleInfoDto);
        }
        cacheInfo(StringUtil.objToString(articleInfoDto.getId()),preNextArticleList);
        return articleInfoDto;
    }

    @Async
    void cacheInfo(String id, List<ArticleInfoDto> articleInfoDtoList) {
        BoundHashOperations bho = getOper();
        bho.expire(expire, TimeUnit.SECONDS);
        bho.put(getKey(id), articleInfoDtoList);
    }
}
