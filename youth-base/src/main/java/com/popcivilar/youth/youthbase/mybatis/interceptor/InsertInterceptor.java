package com.popcivilar.youth.youthbase.mybatis.interceptor;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Properties;
import java.util.Random;

/**
 * @desc 用于设置插入的ID
 * @author
 */
@Component
@Intercepts(  {
        @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class,Integer.class })
})
public class InsertInterceptor implements Interceptor {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        if(boundSql.getSql().toUpperCase().startsWith("INSERT")){ // 当是插入时 设置id
            ParameterHandler parameterHandler = statementHandler.getParameterHandler();
            Object paramObj = parameterHandler.getParameterObject();
            PropertyDescriptor pds = BeanUtils.getPropertyDescriptor(paramObj.getClass(),"id");
            if(pds!=null) {
                Method rMethod = pds.getReadMethod();
                if (rMethod != null) { // 插入的时候要判断是不是有id  若果有id 那就不生成id
                    Object o = rMethod.invoke(paramObj);
                    if (o == null) { // 没有主键的时候  就生成id
                        Method method = pds.getWriteMethod();
                        if (method != null) {
                            Random r = new Random();
                            //todo
                            int i = r.nextInt(1000);
                            method.invoke(paramObj, i);
                            System.out.println(paramObj);
                        }
                    }
                }
            }
        }
        Object result = invocation.proceed();
        return result;
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
    }

}
