package com.popcivilar.youth.youthbase.base.service;

import com.github.pagehelper.Page;
import com.popcivilar.youth.youthbase.base.entity.EntityBean;
import com.popcivilar.youth.youthbase.base.entity.UniPage;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;
import java.util.List;


public interface BaseService<T extends EntityBean> extends Mapper<T>, IdsMapper<T> {


    /**
     * 公用分页查询
     *
     * @param record   查询条件
     * @param pageNum  第几页
     * @param pageSize 每页条数
     * @return 分页结果
     */
    Page<T> selectPage(T record, int pageNum, int pageSize);

    /**
     * 公用逻辑删除
     *
     * @param id 对象
     * @return 删除条数
     */
    <K extends Serializable> int deleteByIdLogical(K id);

    /**
     * 逻辑删除多个
     *
     * @param idArr 传入删除者的ids,用逗号拼接的字符串
     * @return 影响的行数
     */
    int deleteMultiByLogical(String[] idArr);

    /**
     * 逻辑删除
     *
     * @param record record中的属性是要删除的条件
     * @return 影响的行数
     */
    int deleteLogical(T record);


    UniPage list(UniParam<T> uniParam);

}

