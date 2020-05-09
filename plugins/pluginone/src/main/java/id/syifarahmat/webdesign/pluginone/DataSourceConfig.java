package id.syifarahmat.webdesign.pluginone;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@Component
public class DataSourceConfig {
    public static final String dataSourceBeanName = "dataSource";
    private final Environment environment;
    public DataSourceConfig(Environment environment) {
        this.environment=environment;
    }
    @Bean(DataSourceConfig.dataSourceBeanName)
    public DataSource dataSource() {
        /* i use DataSource dataSource() because plannig use my spring batis. But spring mybatis required bean name dataSource, do you have a solution */
        return environment.getProperty(DataSourceConfig.dataSourceBeanName, HikariDataSource.class);
    }
}
