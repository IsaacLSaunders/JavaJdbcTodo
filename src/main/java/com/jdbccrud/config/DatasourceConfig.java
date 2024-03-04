package com.jdbccrud.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatasourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.main")
    public HikariDataSource hikariDataSource(){
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }

    //this is done so that we could potentially use multiple datasources
        //right the datasource above is using the app.datasource.main but if we wanted to connect to
        //another database we could create another datasource using a different config
        //this method can then make multiple instances of our jdbcTemplate object that can connect to multiple databases
    @Bean
    public JdbcTemplate jdbcTemplate(HikariDataSource hikariDataSource){
        return new JdbcTemplate(hikariDataSource);
    }

    //this could be another way to create a HikariDataSource
//    @Bean
//    public HikariDataSource testHikariDataSource(){
//        HikariConfig hc = new HikariConfig("src/main/resources/application.properties");
//        return new HikariDataSource(hc);
//    }

}
