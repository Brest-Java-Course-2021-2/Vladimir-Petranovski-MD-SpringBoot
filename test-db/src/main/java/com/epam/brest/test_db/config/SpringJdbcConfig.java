package com.epam.brest.test_db.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class SpringJdbcConfig {

    /**
     * Bean dataSource DataSource.
     *
     * @return dataSource.
     */

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("create-tables.sql")
                .addScript("init-tables.sql")
                .build();
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
