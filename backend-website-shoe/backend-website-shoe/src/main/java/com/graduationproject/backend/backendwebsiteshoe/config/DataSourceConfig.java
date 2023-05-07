package com.graduationproject.backend.backendwebsiteshoe.config;

import java.io.IOException;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 * Config script data source test.
 *
 * @author Mai Ngoc Dinh
 */
@Configuration
public class DataSourceConfig {

  private static final String SQL_PATTERN = "classpath:/templates/data/*.sql";

  /**
   * Set datasource.
   *
   * @return datasource.
   */
  @Bean(name = "dataSource")
  public DataSource getDataSource() throws IOException {
    DataSource dataSource = createDataSource();
    DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
    return dataSource;
  }

  /**
   * Create database populator.
   *
   * @return database.
   */
  private DatabasePopulator createDatabasePopulator() throws IOException {
    ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
    databasePopulator.setContinueOnError(true);

    // TODO: Add script to use test UI
    // databasePopulator.addScript(new ClassPathResource("templates/table/create_table.sql"));
    //  databasePopulator.addScripts(
    //     new PathMatchingResourcePatternResolver().getResources(SQL_PATTERN)
    // );
    return databasePopulator;
  }

  /**
   * Create config datasource.
   *
   * @return datasource.
   */
  private DataSource createDataSource() {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
    dataSourceBuilder.url("jdbc:mysql://localhost:3306/shoes_db");
    dataSourceBuilder.username("root");
    dataSourceBuilder.password("2019605688");
    return dataSourceBuilder.build();
  }

}
