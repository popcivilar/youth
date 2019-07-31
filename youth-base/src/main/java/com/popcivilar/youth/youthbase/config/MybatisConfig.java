package com.popcivilar.youth.youthbase.config;

import com.popcivilar.youth.youthbase.base.mapper.BaseMapper;
import com.popcivilar.youth.youthbase.mybatis.interceptor.InsertInterceptor;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * @desc 拦截器
 * @author
 */
//@Configuration
public class MybatisConfig  {

//    @Bean
    public String myInterceptor(SqlSessionFactory sqlSessionFactory) {
        InsertInterceptor executorInterceptor = new InsertInterceptor();
        Properties properties = new Properties();
        properties.setProperty("prop1","value1");
        executorInterceptor.setProperties(properties);
        sqlSessionFactory.getConfiguration().addInterceptor(executorInterceptor);
        return "interceptor";
    }

}
