package com.epam.brest.dev_db.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@Profile(value = "dev")
@PropertySource({"classpath:application-dev.properties"})
public class SpringJdbcProfileDevConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    /**
     * Bean dataSource DataSource.
     *
     * @return dataSource.
     */

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);

        Resource initSchema = new ClassPathResource("create-tables-postgres.sql");
        Resource initData = new ClassPathResource("init-tables-postgres.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, initData);
        DatabasePopulatorUtils.execute(databasePopulator, dataSourceBuilder.build());

        return dataSourceBuilder.build();
    }

    /**
     * Bean namedParameterJdbcTemplate NamedParameterJdbcTemplate.
     *
     * @return namedParameterJdbcTemplate.
     */

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    /**
     * Bean transactionManager DataSourceTransactionManager.
     *
     * @return dataSourceTransactionManager.
     */

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
