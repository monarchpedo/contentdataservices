package com.storyshell.contentdataservices;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.jersey.JerseyProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.RequestContextFilter;

import com.storyshell.controller.FeedController;
import com.storyshell.controller.MediaController;
import com.storyshell.util.ValidationExceptionMapper;

@Configuration
@EnableConfigurationProperties(JerseyProperties.class)
@ApplicationPath("api/v1")
public class JerseyConfig extends ResourceConfig {
	@Inject
	private JerseyProperties jersey;

	public JerseyConfig() {

	}

	@PostConstruct
	public void init() {
		//packages("com.storyshell.controller");
		register(FeedController.class);
		register(MediaController.class);
		register(GenericExceptionMapper.class);
		register(RequestContextFilter.class);
		register(GenericExceptionHandler.class);
		register(ValidationExceptionMapper.class);
		register(MultiPartFeature.class);
	}

}
