package com.moke.mokeWork.config;

import java.sql.SQLException;

import javax.annotation.PreDestroy;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.moke.mokeWork.config.properties.DataBaseProperties;

@Configuration
@EnableConfigurationProperties({DataBaseProperties.class})
@MapperScan(basePackages = {"com.moke.mokeWork.dao.rdb"})
public class MyBatisConfig {

	@Autowired
    private DataBaseProperties dataBaseProperties;

    private DruidDataSource dataSource;

    //数据库连接池
    @Bean(destroyMethod = "close")
    public DruidDataSource createDataSource() {

        this.dataSource = new DruidDataSource();
        this.dataSource.setDriverClassName(dataBaseProperties.getDriverClassName());
        this.dataSource.setUrl(dataBaseProperties.getUrl());
        this.dataSource.setUsername(dataBaseProperties.getUsername());
        this.dataSource.setPassword(dataBaseProperties.getPassword());
        this.dataSource.setInitialSize(dataBaseProperties.getInitialSize());
        this.dataSource.setMaxActive(dataBaseProperties.getMaxActive());
        this.dataSource.setMaxWait(dataBaseProperties.getMaxWait());
        this.dataSource.setMinIdle(dataBaseProperties.getMinIdle());
        this.dataSource.setValidationQuery(dataBaseProperties.getValidationQuery());
        this.dataSource.setTestOnBorrow(Boolean.valueOf(dataBaseProperties.getTestOnBorrow()));
        this.dataSource.setTestOnReturn(Boolean.valueOf(dataBaseProperties.getTestOnReturn()));
        this.dataSource.setTestWhileIdle(Boolean.valueOf(dataBaseProperties.getTestWhileIdle()));
        this.dataSource.setMinEvictableIdleTimeMillis(dataBaseProperties.getMinEvictableIdleTimeMillis());
        this.dataSource.setTimeBetweenEvictionRunsMillis(dataBaseProperties.getTimeBetweenEvictionRunsMillis());
        this.dataSource.setPoolPreparedStatements(dataBaseProperties.isPoolPreparedStatements());
        try {
			this.dataSource.setFilters(dataBaseProperties.getFilters());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.dataSource.setMaxPoolPreparedStatementPerConnectionSize(dataBaseProperties.getMaxPoolPreparedStatementPerConnectionSize());
        this.dataSource.setConnectionProperties(dataBaseProperties.getConnectionProperties());
        this.dataSource.setUseGlobalDataSourceStat(dataBaseProperties.isUseGlobalDataSourceStat());

        return this.dataSource;
    }

    @PreDestroy
    public void close(){
        if(this.dataSource != null){
            this.dataSource.close();
        }
    }

    @Bean
    @Lazy
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(createDataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:com/moke/mokeWork/dao/rdb/mapper/*Mapper.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    @Lazy
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager((createDataSource()));
    }
}
