package com.cartexample.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations={"classpath*:context.xml"})
@ComponentScan("com.cartexample.app")
public class HelloWorldSpringBoot {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(HelloWorldSpringBoot.class);
		logger.info("TEST");
		SpringApplication.run(HelloWorldSpringBoot.class, args);
	}
}
