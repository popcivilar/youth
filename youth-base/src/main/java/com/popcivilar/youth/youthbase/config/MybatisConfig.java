package com.popcivilar.youth.youthbase.config;

import com.popcivilar.youth.youthbase.mybatis.interceptor.InsertInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @desc 拦截器
 * @author
 */
@Configuration
public class MybatisConfig  {

    @Bean
    public String myInterceptor(SqlSessionFactory sqlSessionFactory) {
        InsertInterceptor executorInterceptor = new InsertInterceptor();
        Properties properties = new Properties();
        properties.setProperty("prop1","value1");
        executorInterceptor.setProperties(properties);
        sqlSessionFactory.getConfiguration().addInterceptor(executorInterceptor);
        return "interceptor";
    }
}
