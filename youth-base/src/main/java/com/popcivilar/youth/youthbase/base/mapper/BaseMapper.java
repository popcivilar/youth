package com.popcivilar.youth.youthbase.base.mapper;

import com.popcivilar.youth.youthbase.base.entity.UniPage;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

/**
 * myMybatis mapper 封装基类
 * @param <T>
 */
public interface BaseMapper<T> extends Mapper<T>, IdsMapper<T> {

}
