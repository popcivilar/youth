package com.popcivilar.youth.general.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.popcivilar.youth.general.article.dao.ArticleInfoMapper;
import com.popcivilar.youth.general.article.entity.ArticleInfo;
import com.popcivilar.youth.general.article.entity.ArticleInfoDto;
import com.popcivilar.youth.general.article.entity.ArticleView;
import com.popcivilar.youth.general.article.service.ArticleInfoService;
import com.popcivilar.youth.youthbase.base.entity.UniPage;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import com.popcivilar.youth.youthbase.base.service.BaseServiceImpl;
import com.popcivilar.youth.youthbase.common.pic.PicService;
import com.popcivilar.youth.youthbase.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ArticleInfoServiceImpl extends BaseServiceImpl<ArticleInfo> implements ArticleInfoService {

    private static Logger logger = LoggerFactory.getLogger(ArticleInfoServiceImpl.class);

    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    @Autowired
    private PicService picService;

    private RedisTemplate redisTemplate;

    private static final String REDIS_PRE_NEXT_ARTICLE_KEY = "REDIS_PRE_NEXT_ARTICLE_KEY";//上一篇。下一篇


    private static final String REDIS_ARTICLE_RANKING_KEY = "REDIS_ARTICLE_RANKING_KEY";//点击排行

    private static final String REDIS_GENERAL_INDEX_INFO_KEY = "REDIS_GENERAL_INDEX_INFO_KEY";//general服务首页信息key


    private static final String REDIS_ARTICLE_LATEST_KEY = "REDIS_ARTICLE_LATEST_KEY";//最新文章

    private static final String REDIS_ARTICLE_TOP_KEY = "REDIS_ARTICLE_TOP_KEY";//置顶文章


    @Value("${youth.redis.cache.time:3600}")
    private int expire;//过期时间


    @Value("${spring.application.name}")
    private String sysTag;//系统标识

    @Autowired
    public void initRedisTemplate(RedisTemplate redisTemplate) {
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        this.redisTemplate = redisTemplate;
    }

    public BoundHashOperations getOper(String boKey) {
        return redisTemplate.boundHashOps(boKey);
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

        BoundHashOperations bho = getOper(REDIS_PRE_NEXT_ARTICLE_KEY);
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
        cacheInfo(REDIS_PRE_NEXT_ARTICLE_KEY,StringUtil.objToString(articleInfoDto.getId()),preNextArticleList);
        return articleInfoDto;
    }

    @Async
    void cacheInfo(String boKey,String key, Object obj) {
        BoundHashOperations bho = getOper(boKey);
        bho.expire(expire, TimeUnit.SECONDS);
        bho.put(getKey(key), obj);
    }

    @Override
    public void putArticleRankindInRedis(ArticleInfoDto articleInfoDto) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        String jsonString = JSON.toJSONString(articleInfoDto);
        zSetOperations.incrementScore(REDIS_ARTICLE_RANKING_KEY,jsonString,1);
    }

    @Override
    public List<ArticleInfoDto> getArticleRankindInRedis() {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        Set<String> stringJsonSet = zSetOperations.reverseRange(REDIS_ARTICLE_RANKING_KEY, 0, 7);
        List<ArticleInfoDto> returnList = new LinkedList<>();
        if(!stringJsonSet.isEmpty()){
            ArticleInfoDto articleInfoDto;
            for (String k : stringJsonSet) {
                if(!k.contains("id")){
                    continue;
                }
                articleInfoDto =  JSON.parseObject(k,ArticleInfoDto.class);
                if(articleInfoDto != null){
                    returnList.add(articleInfoDto);
                }
            }
        }
        return returnList;
    }

    @Override
    public Integer countArticle() {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setDeletedFlag("0");
        return articleInfoMapper.selectCount(articleInfo);
    }

    @Override
    public List<ArticleView> queryLatest(int indexShowNum) {
        BoundHashOperations bho = getOper(REDIS_GENERAL_INDEX_INFO_KEY);
        String key = getKey(REDIS_ARTICLE_LATEST_KEY);
        if (bho.hasKey(key)) {
            logger.debug("从Redis 获取【{}】代码表", key);
            List<ArticleView> articleViewList = (List<ArticleView>) bho.get(key);
            return articleViewList;
        }

        List<ArticleView> articleViewList = articleInfoMapper.queryLatest(indexShowNum);
        articleViewList = setCachePic(articleViewList, 3);
        cacheInfo(REDIS_GENERAL_INDEX_INFO_KEY,REDIS_ARTICLE_LATEST_KEY,articleViewList);
        return articleViewList;

    }

    @Override
    public List<ArticleView> queryByParam(ArticleInfoDto articleInfoDto) {
        return articleInfoMapper.queryByParam(articleInfoDto);
    }


    private List<ArticleView> setCachePic(List<ArticleView> articleViews,int indexShowNum){
        if( articleViews != null && articleViews.size() > 0 ){
            for(ArticleView articleView : articleViews){
                articleView.setPicShowList(picService.getPicList(indexShowNum));
            }
        }
        return articleViews;
    }

    @Override
    public List<ArticleView> queryTopArticle() {
        BoundHashOperations bho = getOper(REDIS_GENERAL_INDEX_INFO_KEY);
        String key = getKey(REDIS_ARTICLE_TOP_KEY);
        if (bho.hasKey(key)) {
            logger.debug("从Redis 获取【{}】代码表", key);
            List<ArticleView> articleViewList = (List<ArticleView>) bho.get(key);
            return articleViewList;
        }
        ArticleInfoDto articleInfoDto = new ArticleInfoDto();
        articleInfoDto.setAttrFlagArr("9".split(","));
        List<ArticleView> articleViewList = this.queryByParam(articleInfoDto);
        articleViewList = setCachePic(articleViewList, 2);
        cacheInfo(REDIS_GENERAL_INDEX_INFO_KEY,REDIS_ARTICLE_TOP_KEY,articleViewList);
        return articleViewList;
    }
}
