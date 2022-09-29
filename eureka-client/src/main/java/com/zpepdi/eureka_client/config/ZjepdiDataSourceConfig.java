package com.zpepdi.eureka_client.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.zpepdi.eureka_client.dao.zjepdi",sqlSessionFactoryRef = "zjepdiSqlSessionFactory")
public class ZjepdiDataSourceConfig {
        static final String MAPPER_LOCATION = "classpath:/mapper/zjepdi/*.xml";
        @Value("${spring.datasource.druid.zjepdi.url}")
        private String url;
        @Value("${spring.datasource.druid.zjepdi.username}")
        private String username;
        @Value("${spring.datasource.druid.zjepdi.password}")
        private String password;
        @Value("${spring.datasource.druid.zjepdi.driver-class-name}")
        private String driverClassName;
    
        @Bean("zjepdiDataSource")
        @Qualifier("zjepdiDataSource")
        public DruidDataSource dataSource() {
            DruidDataSource datasource = new DruidDataSource();
            datasource.setUrl(url);
            datasource.setUsername(username);
            datasource.setPassword(password);
            datasource.setDriverClassName(driverClassName);
            return datasource;
        }

//        @Bean("zjepdiDataSource")
//        @ConfigurationProperties(prefix = "spring.datasource.zjepdi")
//        public DataSource getZjepdiDataSource(){
//            return DataSourceBuilder.create().build();
//        }

        @Bean(name = "zjepdiTransactionManager")
        @Qualifier("zjepdiDataSource")
        public DataSourceTransactionManager zjepdiTransactionManager(@Qualifier("zjepdiDataSource") DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }

        @Bean(name = "zjepdiSqlSessionFactory")
        @Qualifier("zjepdiDataSource")
        public SqlSessionFactory zjepdiSqlSessionFactory(@Qualifier("zjepdiDataSource") DataSource datasource) throws Exception {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(datasource);
            bean.setMapperLocations( new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
            return bean.getObject();
        }

        /**
         * 配置会话
         * @param sessionFactory
         * @return
         */
        @Bean("zjepdiSqlSessionTemplate")
        @Qualifier("zjepdiDataSource")
        public SqlSessionTemplate zjepdiSqlsessionTemplate(@Qualifier("zjepdiSqlSessionFactory") SqlSessionFactory sessionFactory) {
            return new SqlSessionTemplate(sessionFactory);
        }
}
