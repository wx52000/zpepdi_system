package zpepdi.system.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "zpepdi.system.dao.fd",sqlSessionFactoryRef = "fdSqlSessionFactory")
public class FdDataSourceConfig {
        static final String MAPPER_LOCATION = "classpath:/mapper/fd/*.xml";
        @Value("${spring.datasource.druid.fd.url}")
        private String url;
        @Value("${spring.datasource.druid.fd.username}")
        private String username;
        @Value("${spring.datasource.druid.fd.password}")
        private String password;
        @Value("${spring.datasource.druid.fd.driver-class-name}")
        private String driverClassName;

        @Bean("fdDataSource")
        @Primary
        public DruidDataSource dataSource() {
            DruidDataSource datasource = new DruidDataSource();
            datasource.setUrl(url);
            datasource.setUsername(username);
            datasource.setPassword(password);
            datasource.setDriverClassName(driverClassName);
            return datasource;
        }

//        @Bean("fdDataSource")
//        @ConfigurationProperties(prefix = "spring.datasource.fd")
//        public DataSource getAppraiseDataSource(){
//            return DataSourceBuilder.create().build();
//        }

        @Bean("fdTransactionManager")
        @Primary
        public DataSourceTransactionManager transactionManager(@Qualifier("fdDataSource") DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }

        @Bean("fdSqlSessionFactory")
        @Primary
        public SqlSessionFactory fdSqlSessionFactory(@Qualifier("fdDataSource") DataSource dataSource) throws Exception {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dataSource);
            bean.setVfs(SpringBootVFS.class);
            bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
            bean.setTypeAliasesPackage("zpepdi.system.entity");
            bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
            return bean.getObject();
        }
        @Bean("fdSqlSessionTemplate")
        @Primary
        public SqlSessionTemplate fdSqlSessionTemplate(@Qualifier("fdSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
            return new SqlSessionTemplate(sqlSessionFactory);
        }
}
