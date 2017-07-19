package com.storyshell.contentdataservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Raja Bose! Software developer
 *
 */
@SpringBootConfiguration
@ComponentScan(basePackages = { "com.storyshell" })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	// public void onStartup(ServletContext sc) throws ServletException {
	// TODO Auto-generated method stub
	/*
	 * sc.setInitParameter("contextConfigLocation", "noop");
	 * 
	 * AnnotationConfigWebApplicationContext context = new
	 * AnnotationConfigWebApplicationContext();
	 * context.register(AppConfig.class); sc.addListener(new
	 * ContextLoaderListener(context)); sc.addListener(new
	 * RequestContextListener());
	 */
	// }
}
