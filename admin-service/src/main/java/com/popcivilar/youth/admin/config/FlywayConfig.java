package com.popcivilar.youth.admin.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
public class FlywayConfig {

    @Value("${flywaydb.locations}")
    String sqlLocation;

    @Bean(name = "flyway")
    public Flyway flywayNotADestroyer(DataSource dataSource) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setBaselineOnMigrate(true);
        flyway.setBaselineVersionAsString("0.0.1");
        flyway.setLocations(sqlLocation+"/blog");

        // 需要修改Schema管理的表名，以区别不同应用/服务
        flyway.setTable("blog_flyway_db_service");

        try {
            flyway.migrate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flyway;
    }
}
