package com.hzvtc.starrynight;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hzvtc.starrynight.mapper")
public class IntothestarrynightApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntothestarrynightApplication.class, args);
	}
}
