package com.popcivilar.youth.youthbase.exception;

import com.popcivilar.youth.youthbase.base.entity.ModuleReturn;
import com.popcivilar.youth.youthbase.token.TokenPass;
import com.popcivilar.youth.youthbase.utils.StringUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobeExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @ExceptionHandler(value = TokenException.class)
    public ModuleReturn exceptionTokenHanlder(HttpServletRequest req, HttpServletResponse res, Exception e) {
        doLogger(req, res, e);
        logger.error(e.getMessage());
        String returnMsg = e.getMessage();
        // 当某些提示信息过长的时候  截取一下
        if(StringUtil.isNotNullOrEmpty(returnMsg)){
            if(returnMsg.length() > 200){
                returnMsg = returnMsg.substring(0, 200)+"...";
            }
        }
        return ModuleReturn.fail("401",null,returnMsg);
    }



    @ExceptionHandler(value = Exception.class)
    public ModuleReturn exceptionHanlder(HttpServletRequest req, HttpServletResponse res, Exception e) {
        doLogger(req, res, e);
        logger.error(e.getMessage());
        String returnMsg = e.getMessage();
        // 当某些提示信息过长的时候  截取一下
        if(StringUtil.isNotNullOrEmpty(returnMsg)){
            if(returnMsg.length() > 200){
                returnMsg = returnMsg.substring(0, 200)+"...";
            }
        }
        return ModuleReturn.fail(returnMsg);
    }

    private void doLogger(HttpServletRequest req, HttpServletResponse res, Exception e) {
        logger.error("请求状态：" + res.getStatus());
        logger.error("请求URL:" + req.getRequestURI());
        logger.error("请求方法：" + req.getMethod());
        logger.error("全局异常拦截:", e);
    }


}
