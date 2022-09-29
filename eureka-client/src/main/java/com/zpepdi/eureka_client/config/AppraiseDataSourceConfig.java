package com.zpepdi.eureka_client.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.zpepdi.eureka_client.dao.appraise",sqlSessionFactoryRef = "appraiseSqlSessionFactory")
public class AppraiseDataSourceConfig {
        static final String MAPPER_LOCATION = "classpath:/mapper/appraise/*.xml";
        @Value("${spring.datasource.druid.appraise.url}")
        private String url;
        @Value("${spring.datasource.druid.appraise.username}")
        private String username;
        @Value("${spring.datasource.druid.appraise.password}")
        private String password;
        @Value("${spring.datasource.druid.appraise.driver-class-name}")
        private String driverClassName;

        @Bean("appraiseDataSource")
        @Primary
        public DruidDataSource dataSource() {
            DruidDataSource datasource = new DruidDataSource();
            datasource.setUrl(url);
            datasource.setUsername(username);
            datasource.setPassword(password);
            datasource.setDriverClassName(driverClassName);
            return datasource;
        }

//        @Bean("appraiseDataSource")
//        @ConfigurationProperties(prefix = "spring.datasource.appraise")
//        public DataSource getAppraiseDataSource(){
//            return DataSourceBuilder.create().build();
//        }

        @Bean("appraiseTransactionManager")
        @Primary
        public DataSourceTransactionManager transactionManager(@Qualifier("appraiseDataSource") DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }

        @Bean("appraiseSqlSessionFactory")
        @Primary
        public SqlSessionFactory appraiseSqlSessionFactory(@Qualifier("appraiseDataSource") DataSource dataSource) throws Exception {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dataSource);
            bean.setVfs(SpringBootVFS.class);
            bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
            bean.setTypeAliasesPackage("com.zpepdi.eureka_client.entity");
            bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
            return bean.getObject();
        }
        @Bean("appraiseSqlSessionTemplate")
        @Primary
        public SqlSessionTemplate appraiseSqlSessionTemplate(@Qualifier("appraiseSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
            return new SqlSessionTemplate(sqlSessionFactory);
        }
}
