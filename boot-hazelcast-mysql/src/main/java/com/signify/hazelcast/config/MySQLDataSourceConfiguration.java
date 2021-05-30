package com.signify.hazelcast.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:database.properties")
public class MySQLDataSourceConfiguration {

	@Autowired
	private Environment environment;
	
	private final String URL = "spring.datasource.url";
	private final String USER = "spring.datasource.username";
	private final String DRIVER = "spring.datasource.driver-class-name";
	private final String PASSWORD = "spring.datasource.password";

	@Bean(destroyMethod = "")
	@Qualifier("mySQLDataSource")
	public DataSource msSQLExtranetDataSourceLocal() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(environment.getProperty(URL));
		dataSource.setUsername(environment.getProperty(USER));
		dataSource.setPassword(environment.getProperty(PASSWORD));
		dataSource.setDriverClassName(environment.getProperty(DRIVER));
		return dataSource;
	}
}
