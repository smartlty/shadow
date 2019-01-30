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
public class PostgresConfig {

    @Value("${postgresql.datasource.r.jdbc-url}")
    private String urlR;

    @Value("${postgresql.datasource.r.username}")
    private String userNameR;

    @Value("${postgresql.datasource.r.password}")
    private String passwordR;

    @Value("${postgresql.datasource.w.jdbc-url}")
    private String urlW;

    @Value("${postgresql.datasource.w.username}")
    private String userNameW;

    @Value("${postgresql.datasource.w.password}")
    private String passwordW;

    @Value("${postgresql.datasource.diverClassName}")
    private String diverClassName;

    /**
     * PostgreSQL datasource ReadOnly definition.
     */
    private DataSource postgresRDataSource() {
        return BaseConfig.createDataSource(urlR, userNameR, passwordR, diverClassName);
    }

    /**
     * PostgreSQL datasource Write definition.
     */
    private DataSource postgresWDataSource() {
        return BaseConfig.createDataSource(urlW, userNameW, passwordW, diverClassName);
    }

    @Bean
    public DataSource dynamicDataSource() {
        DataSource PostgresW = postgresWDataSource();
        DataSource PostgresR = postgresRDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(PostgresDBContextHolder.POSTGRES_R, PostgresR);
        targetDataSources.put(PostgresDBContextHolder.POSTGRES_W, PostgresW);

        PostgresDynamicDataSource dynamicDataSource = new PostgresDynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dynamicDataSource.setDefaultTargetDataSource(PostgresR);//配置默认的数据源

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
