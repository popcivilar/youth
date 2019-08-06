package com.popcivilar.youth.youthbase.base.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.popcivilar.youth.youthbase.base.entity.EntityBean;
import com.popcivilar.youth.youthbase.base.entity.UniPage;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import com.popcivilar.youth.youthbase.base.mapper.BaseMapper;
import com.popcivilar.youth.youthbase.exception.BusinessException;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class BaseServiceImpl<T extends EntityBean> implements BaseService<T> {

    @Autowired
    private BaseMapper<T> mapper;

    public Page<T> selectPage(T record, int pageNum, int pageSize) {
        Page<T> page = PageHelper.startPage(pageNum, pageSize);
        mapper.select(record);
        return page;
    }

    public <K extends Serializable> int deleteByIdLogical(K id) {
        T bean = this.getBean(id);
        bean.setDeletedFlag("1");
        return mapper.updateByPrimaryKeySelective(bean);
    }

    public int deleteMultiByLogical(String[] idArr) {
        int i = 0;
        if (idArr != null && idArr.length > 0) {
            for (String id : idArr) {
                if(this.deleteByIdLogical(id) > 0 ){
                    i ++ ;
                }
            }
        }
        return i;
    }

    public int deleteLogical(T record) {
        record.setDeletedFlag("1");
        return mapper.updateByPrimaryKeySelective(record);
    }

    public int deleteByPrimaryKey(Object o) {
        return mapper.deleteByPrimaryKey(o);
    }

    public int delete(T t) {
        return mapper.delete(t);
    }

    public int insert(T t) {
        return mapper.insert(t);
    }

    public int insertSelective(T t) {
        return mapper.insertSelective(t);
    }

    public boolean existsWithPrimaryKey(Object o) {
        return mapper.existsWithPrimaryKey(o);
    }

    public List<T> selectAll() {
        return mapper.selectAll();
    }

    public T selectByPrimaryKey(Object o) {
        return mapper.selectByPrimaryKey(o);
    }

    public int selectCount(T t) {
        return mapper.selectCount(t);
    }

    public List<T> select(T t) {
        return mapper.select(t);
    }

    public T selectOne(T t) {
        return mapper.selectOne(t);
    }

    public int updateByPrimaryKey(T t) {
        return mapper.updateByPrimaryKey(t);
    }

    public int updateByPrimaryKeySelective(T t) {
        t.setModifyDate(new Date());
        return mapper.updateByPrimaryKeySelective(t);
    }

    public int deleteByExample(Object o) {
        return mapper.deleteByExample(o);
    }

    public List<T> selectByExample(Object o) {
        return mapper.selectByExample(o);
    }

    public int selectCountByExample(Object o) {
        return mapper.selectCountByExample(o);
    }

    public int updateByExample(T t, Object o) {
        return mapper.updateByExample(t,o);
    }

    public int updateByExampleSelective(T t, Object o) {
        return mapper.updateByExampleSelective(t,o);
    }

    public int deleteByIds(String s) {
        return mapper.deleteByIds(s);
    }

    public List<T> selectByIds(String s) {
        return mapper.selectByIds(s);
    }

    public List<T> selectByExampleAndRowBounds(Object o, RowBounds rowBounds) {
        return mapper.selectByExampleAndRowBounds(o,rowBounds);
    }

    public List<T> selectByRowBounds(T t, RowBounds rowBounds) {
        return mapper.selectByRowBounds(t,rowBounds);
    }

    /**
     * 得到一个填充了主键的业务实体类
     * @param id 主键id
     * @param <K> 主键类型
     * @return
     */
    protected  <K extends Serializable> T getBean(K id) {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Class<T> clazz = (Class) params[0] ;
        T bean;
        try {
            bean = clazz.newInstance();
        } catch (Exception e) {
            throw new BusinessException("实例化对象失败",e);
        }
        bean.setId(id);
        return bean;
    }

    public T selectOneByExample(Object o) {
        return null;
    }


    public UniPage list(UniParam<T> uniParam) {
        T t = uniParam.getInParam();
        Page<T> page = this.selectPage(t, uniParam.getPage(), uniParam.getPageSize());
        return new UniPage(page);
    }

}
