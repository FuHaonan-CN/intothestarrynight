package com.hzvtc.starrynight.comm.config;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
//public class WebConfiguration extends WebMvcConfigurationSupport{
public class WebConfiguration implements WebMvcConfigurer{

//	@Override
//	public void addViewControllers(ViewControllerRegistry registry){
//		registry.addViewController()
//	}
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedHeaders("*")
				.allowedMethods("*")
				.allowedOrigins("*");
	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
		configurer.setDefaultTimeout(30000);
	}
}
