package com.popcivilar.youth.general.type.service.impl;

import com.popcivilar.youth.general.article.dao.ArticleInfoMapper;
import com.popcivilar.youth.general.article.entity.ArticleInfoDto;
import com.popcivilar.youth.general.article.service.ArticleInfoService;
import com.popcivilar.youth.general.type.entity.ArticleTabNav;
import com.popcivilar.youth.general.type.entity.TypeInfo;
import com.popcivilar.youth.general.type.entity.TypeInfoDto;
import com.popcivilar.youth.general.type.service.TypeInfoService;
import com.popcivilar.youth.youthbase.base.service.BaseServiceImpl;
import com.popcivilar.youth.youthbase.common.pic.PicService;
import com.popcivilar.youth.youthbase.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class TypeInfoServiceImpl extends BaseServiceImpl<TypeInfo> implements TypeInfoService {

    private static Logger logger = LoggerFactory.getLogger(TypeInfoServiceImpl.class);


    @Value("${youth.redis.cache.time:3600}")
    private int expire;//过期时间


    @Value("${spring.application.name}")
    private String sysTag;//系统标识

    @Autowired
    private PicService picService;

    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    private RedisTemplate redisTemplate;

    private static final String REDIS_GENERAL_INDEX_INFO_KEY = "REDIS_GENERAL_INDEX_INFO_KEY";//general服务首页信息key

    private static final String TYPE_CLOUD_KEY = "TYPE_CLOUD_KEY";//标签云

    private static final String ARTICLE_TAB_NAV = "ARTICLE_TAB_NAV";//文章 TAB NAV


    public BoundHashOperations getOper() {
        return redisTemplate.boundHashOps(REDIS_GENERAL_INDEX_INFO_KEY);
    }


    @Autowired
    public void initRedisTemplate(RedisTemplate redisTemplate) {
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        this.redisTemplate = redisTemplate;
    }

    /**
     *
     * @param key
     * @param object
     * @param localExpire 局部时间
     */
    @Async
    void cacheInfo(String key,Object object,Integer localExpire) {
        int expireTime = localExpire == null?0:localExpire;
        if(localExpire == null){
            expireTime = expire;
        }
        BoundHashOperations bho = getOper();
        bho.expire(expireTime, TimeUnit.SECONDS);
        bho.put(key,object);
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
    public List<TypeInfo> getTypeInfoCloudInRedis() {
        //1.从redis 中获取
        BoundHashOperations bho = getOper();
        String key = getKey(StringUtil.objToString(TYPE_CLOUD_KEY));//标签云
        if (bho.hasKey(key)) {
            logger.debug("从Redis 获取标签数据:{}-{}",REDIS_GENERAL_INDEX_INFO_KEY,TYPE_CLOUD_KEY);
            List<TypeInfo> typeInfoDtoList = (List<TypeInfo>)bho.get(key);
            return typeInfoDtoList;
        }
        //2.没有从db中获取，缓存到redis
        TypeInfo typeInfo = new TypeInfo();
        typeInfo.setDeletedFlag("0");
        List<TypeInfo> typeInfoList = this.select(typeInfo);
        cacheInfo(getKey(TYPE_CLOUD_KEY),typeInfoList,null);
        return typeInfoList;
    }

    @Override
    public List<ArticleTabNav> getArticleTabNav() {
        //1.从redis 中获取
        BoundHashOperations bho = getOper();
        String key = getKey(StringUtil.objToString(ARTICLE_TAB_NAV));//文章 TAB NAV
        if (bho.hasKey(key)) {
            logger.debug("从Redis 获取标签数据:{}-{}",REDIS_GENERAL_INDEX_INFO_KEY,ARTICLE_TAB_NAV);
            List<ArticleTabNav> articleTabNavList = (List<ArticleTabNav>)bho.get(key);
            return articleTabNavList;
        }
        //2.没有从db中获取，缓存到redis
        List<ArticleTabNav> articleTabNavList = new ArrayList<>();
        List<TypeInfo> typeInfoList = getTypeInfoCloudInRedis();
        if(typeInfoList != null && typeInfoList.size() > 0){
            for (TypeInfo typeInfo : typeInfoList) {
                ArticleTabNav articleTabNav = new ArticleTabNav();
                BeanUtils.copyProperties(typeInfo,articleTabNav);
                //picList
                articleTabNav.setPicUrlList(picService.getPicList(2));
                //articleList
                ArticleInfoDto paramDto = new ArticleInfoDto();
                paramDto.setTypeId(typeInfo.getId());
                paramDto.setAttrFlagArr("1,9".split(","));
                articleTabNav.setArticleViewList(articleInfoMapper.queryByParam(paramDto));
                articleTabNavList.add(articleTabNav);
            }
        }
        cacheInfo(getKey(ARTICLE_TAB_NAV),articleTabNavList,null);
        return articleTabNavList;
    }
}
