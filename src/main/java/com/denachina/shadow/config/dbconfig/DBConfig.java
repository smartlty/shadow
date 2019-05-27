package com.denachina.shadow.config.dbconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages={"com.denachina.shadow.dao"}, sqlSessionTemplateRef = "shadowSqlSessionTemplate")
@PropertySource(value = "classpath:db.properties")
public class DBConfig {

    @Value("${datasource.r.jdbc-url}")
    private String urlR;

    @Value("${datasource.r.username}")
    private String userNameR;

    @Value("${datasource.r.password}")
    private String passwordR;

    @Value("${datasource.w.jdbc-url}")
    private String urlW;

    @Value("${datasource.w.username}")
    private String userNameW;

    @Value("${datasource.w.password}")
    private String passwordW;

    @Value("${datasource.diverClassName}")
    private String diverClassName;

    /**
     * datasource ReadOnly definition.
     */
    private DataSource dataSourceR() {
        return BaseConfig.createDataSource(urlR, userNameR, passwordR, diverClassName);
    }

    /**
     * datasource Write definition.
     */
    private DataSource dataSourceW() {
        return BaseConfig.createDataSource(urlW, userNameW, passwordW, diverClassName);
    }

    @Bean
    public DataSource dynamicDataSource() {
        DataSource dbw = dataSourceW();
        DataSource dbr = dataSourceR();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBContextHolder.DB_R, dbr);
        targetDataSources.put(DBContextHolder.DB_W, dbw);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 该方法是AbstractRoutingDataSource的方法
        dynamicDataSource.setTargetDataSources(targetDataSources);
        //配置默认的数据源
        dynamicDataSource.setDefaultTargetDataSource(dbr);

        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory shadowSqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath:/shadowMapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate shadowSqlSessionTemplate(@Qualifier("shadowSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
