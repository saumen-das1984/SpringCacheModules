package com.signify.cacheresolver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Configuration
@PropertySource("classpath:database.properties")
@ConfigurationProperties("book")
@Data
@NoArgsConstructor
@ToString
public class BookQueryConfig {
	public String select;
	public String selectbyId;
	public String selectbyTitle;
	public String update;
	public String insert;
	public String delete;
}
