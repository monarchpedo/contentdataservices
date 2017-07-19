package com.storyshell.contentdataservices;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
@Import(JerseyConfig.class)
public class AppConfig {
	@Inject
	private Environment env;

	@Bean
	public DataSource getDataSource() {
		System.out.println(env.getProperty("jdbc.username"));
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		return dataSource;
	}

	@Bean
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		return jdbcTemplate;
	}

	// @Bean
	// public ServletRegistrationBean jerseyServlet() {
	// ServletRegistrationBean registration = new ServletRegistrationBean(new
	// ServletContainer(), "/rest/*");
	// // our rest resources will be available in the path /rest/*
	// registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS,
	// JerseyConfig.class.getName());
	// return registration;
	// }

}
