package com.popcivilar.youth.youthbase.base.controller;

import com.popcivilar.youth.youthbase.base.entity.EntityBean;
import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
import com.popcivilar.youth.youthbase.base.entity.UniParam;
import com.popcivilar.youth.youthbase.base.service.BaseService;
import com.popcivilar.youth.youthbase.exception.FrameException;
import com.popcivilar.youth.youthbase.utils.ResUtils;
import com.popcivilar.youth.youthbase.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @RequestMapping(value="/deleteOne",method= RequestMethod.DELETE)
    public  @ResponseBody
    ModuleReturn<DTO> deleteOne(@RequestParam("id") String id){
        ModuleReturn<DTO> result = ModuleReturn.success();
        T entityBean = getEntityBean();
        entityBean.setId(Integer.parseInt(id));
        entityBean.setModifyDate(new Date());
        entityBean.setDeletedFlag("1");
        int updateNum = service.updateByPrimaryKeySelective(entityBean);
        if(updateNum <= 0){
            result.setCode(ResUtils.ERROR);
            result.setReturnMsg("删除数据出错");
        }
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


    /**
     * 初始化Bean
     * @param t
     * @return
     */
    public T initalBean(T t){
        if (t == null) {
            return null;
        }
        t.setDeletedFlag("0");
        t.setCreateDate(new Date());
        return t;
    }

    /**
     * 公共的赋值方法
     *
     * @param req
     * @param dto
     * @return
     */
    public <T> UniParam<T> initUniParam(HttpServletRequest req, T dto) {
        UniParam<T> uniParam = new UniParam<T>();
        String pageNo = req.getParameter("pageNo") == null ? "1" : StringUtil.objToString(req.getParameter("pageNo"));
        String pageSize = req.getParameter("pageSize") == null ? "10" : StringUtil.objToString(req.getParameter("pageSize"));
        uniParam.setPage(Integer.parseInt(pageNo));
        uniParam.setPageSize(Integer.parseInt(pageSize));
        uniParam.setSort(req.getParameterValues("sort"));
        uniParam.setSortName(req.getParameterValues("sortName"));
        uniParam.setLanguageCode(null);
        uniParam.setInParam(dto);
        return uniParam;
    }


    //解析前端 传来的时间
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        // / CustomDateEditor为自定义日期编辑器
    }

}
