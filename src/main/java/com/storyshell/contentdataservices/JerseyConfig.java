package com.storyshell.contentdataservices;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.jersey.JerseyProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.RequestContextFilter;

@Configuration
// @AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(JerseyProperties.class)
@ApplicationPath("api/v1")
public class JerseyConfig extends ResourceConfig {
	@Inject
	private JerseyProperties jersey;

	public JerseyConfig() {

	}

	@PostConstruct
	public void init() {
		packages("com.storyshell.controller");
		register(GenericExceptionMapper.class);
		register(RequestContextFilter.class);
	}

	/*
	 * @Bean
	 * 
	 * @ConditionalOnMissingBean public FilterRegistrationBean
	 * requestContextFilter() { FilterRegistrationBean registration = new
	 * FilterRegistrationBean(); //registration.setFilter(new
	 * RequestContextFilter()); registration.setFilter(new ServletContainer());
	 * //registration.addInitParameter("com.sun.jersey.config.property.packages"
	 * ,""); //registration.setOrder(this.jersey.getFilter().getOrder() - 1);
	 * //registration.setName("requestContextFilter"); return registration; }
	 */

}
