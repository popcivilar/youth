package com.popcivilar.youth.youthbase.common.pic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PicService {

    private RedisTemplate redisTemplate;

    @Autowired
    public void initRedisTemplate(RedisTemplate redisTemplate) {
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        this.redisTemplate = redisTemplate;
    }

    private static final String REDIS_PIC_SHOW_KEY = "REDIS_PIC_SHOW_KEY";//图片展示

    /**
     * 获取 picUrl
     * @param num
     * @return
     */
    public List<String> getPicList(int num){
        List<String> picList = new ArrayList<String>();
        SetOperations setOperations = redisTemplate.opsForSet();
        if(num > 1){
            picList = setOperations.randomMembers(REDIS_PIC_SHOW_KEY, num);
        }else{
            String pic = setOperations.randomMember(REDIS_PIC_SHOW_KEY).toString();
            picList.add(pic);
        }
        return picList;
    }

    /**
     * 设置 picUrl
     */
    public void setPicList(String picUrl){
        //1.redis 设置值
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add(REDIS_PIC_SHOW_KEY,picUrl);
    }

}
