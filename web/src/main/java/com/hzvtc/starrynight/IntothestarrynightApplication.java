package com.hzvtc.starrynight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
//@MapperScan("com.hzvtc.starrynight.repository")
public class IntothestarrynightApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(IntothestarrynightApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(IntothestarrynightApplication.class, args);
	}
}
