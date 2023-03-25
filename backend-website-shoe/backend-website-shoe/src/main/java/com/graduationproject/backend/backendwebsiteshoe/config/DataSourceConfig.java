package com.graduationproject.backend.backendwebsiteshoe.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataSourceConfig {

    private static final String SQL_PATTERN = "classpath:/templates/data/*.sql";

    @Bean(name = "dataSource")
    public DataSource getDataSource() throws IOException {
        DataSource dataSource = createDataSource();
        DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
        return dataSource;
    }

    private DatabasePopulator createDatabasePopulator() throws IOException {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(new ClassPathResource("templates/table/create_table.sql"));
        databasePopulator.addScripts(
                new PathMatchingResourcePatternResolver().getResources(SQL_PATTERN)
        );
        return databasePopulator;
    }

    private DataSource createDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/shoes_db");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("2019605688");
        return dataSourceBuilder.build();
    }

}
