package com.popcivilar.youth.youthbase.base.mapper;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * myMybatis mapper 封装基类
 * @param <T>
 */
public interface BaseMapper<T> extends Mapper<T>, IdsMapper<T> {

}
