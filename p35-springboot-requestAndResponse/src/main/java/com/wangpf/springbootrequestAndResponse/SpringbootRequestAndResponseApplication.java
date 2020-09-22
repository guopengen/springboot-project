package com.wangpf.springbootrequestAndResponse;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableCaching
public class SpringbootRequestAndResponseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRequestAndResponseApplication.class, args);
	}

}
