package com.popcivilar.youth.youthbase.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ConditionalOnProperty(name = "spring.datasource.druid.enable")
public class DruidConfiguration {
    private Logger logger = LoggerFactory.getLogger(DruidConfiguration.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.druid.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.druid.filters}")
    private String filters;

    @Value("{spring.datasource.druid.connectionProperties}")
    private String connectionProperties;

    @Bean     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource(){
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            List<Filter> filterList = new ArrayList<Filter>();
            if (filters != null && filters.length() > 0) {
                String[] filterArray = filters.split(",");

                for (String filter : filterArray) {
                    if ("statFilter".equals(filter.trim())) {
                        filterList.add(statFilter());
                    }

                    if ("wallFilter".equals(filter.trim())) {
                        filterList.add(wallFilter());
                    }

                    if ("logFilter".equals(filter.trim())) {
                        filterList.add(logFilter());
                    }
                }
                datasource.setProxyFilters(filterList);
            }

        } catch (Exception e) {
            logger.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(connectionProperties);

        return datasource;
    }

    @Value("${druid.connectionLogEnabled:false}")
    private boolean connectionLogEnabled;
    @Value("${druid.statementLogEnabled:false}")
    private boolean statementLogEnabled;
    @Value("${druid.resultSetLogEnabled:true}")
    private boolean resultSetLogEnabled;
    @Value("${druid.statementExecutableSqlLogEnable:true}")
    private boolean statementExecutableSqlLogEnable;

    @Bean
    public Slf4jLogFilter logFilter() {
        Slf4jLogFilter logFilter = new Slf4jLogFilter();
        logFilter.setConnectionLogEnabled(connectionLogEnabled);
        logFilter.setStatementLogEnabled(statementLogEnabled);
        logFilter.setResultSetLogEnabled(resultSetLogEnabled);
        logFilter.setStatementLogEnabled(statementExecutableSqlLogEnable);
        return logFilter;
    }

    @Value("${durid.slowSqlMillis:5000}")
    private int slowSqlMillis;

//    @Bean
    public StatFilter statFilter() {
        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        statFilter.setSlowSqlMillis(slowSqlMillis);
        return statFilter;
    }

    @Bean
    public WallFilter wallFilter() {
        WallFilter wallFilter = new WallFilter();

        WallConfig wallConfig = new WallConfig();
        wallConfig.setDir("wall");
        wallConfig.setDeleteAllow(true);
        wallConfig.setTruncateAllow(false);
        wallConfig.init();
        wallFilter.setConfig(wallConfig);
        
        return wallFilter;
    }
    

//    public DruidStatInterceptor DruidStatInterceptor(){
//    	return new DruidStatInterceptor();
//    }
//    
//    public JdkRegexpMethodPointcut JdkRegexpMethodPointcut(){
//    	JdkRegexpMethodPointcut jr = new JdkRegexpMethodPointcut();
//    	jr.setPatterns(".*DAO.*",".*ServiceImpl.*");
//    	return jr;
//    }
}