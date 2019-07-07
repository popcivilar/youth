package com.popcivilar.youth.youthbase.base.controller;

import com.popcivilar.youth.youthbase.base.entity.EntityBean;
import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
import com.popcivilar.youth.youthbase.base.service.BaseService;
import com.popcivilar.youth.youthbase.exception.FrameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseController<T extends EntityBean<Integer>,DTO,SERVICE extends BaseService<T>> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SERVICE service;


    @RequestMapping(value="/getOne",method= RequestMethod.GET)
    public  @ResponseBody
    ModuleReturn<DTO> getOne(@RequestParam("id") String id){
        ModuleReturn<DTO> result = ModuleReturn.success();
        T entityBean = getEntityBean();
        entityBean.setId(new Integer(id));
        entityBean = service.selectByPrimaryKey(entityBean);
        if(null == entityBean){
            String message = "Base Controller Select One find no data";
            result.setReturnMsg(message);
            return result;
        }
        DTO dto = getDTO();
        BeanUtils.copyProperties(entityBean,dto);
        result.setData(dto);
        return result;
    }

    private T getEntityBean(){
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Class<T> clazz = (Class) params[0] ;
        T entityBean;
        try {
            entityBean = clazz.newInstance();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new FrameException(e.getMessage());
        }
        return entityBean;
    }

    private DTO getDTO(){
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Class<DTO> clazz = (Class) params[1] ;
        DTO dto;
        try {
            dto = clazz.newInstance();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new FrameException(e.getMessage());
        }
        return dto;
    }
}
