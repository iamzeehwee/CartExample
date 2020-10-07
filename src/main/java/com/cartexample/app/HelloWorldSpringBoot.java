package com.cartexample.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations={"classpath*:context.xml"})
public class HelloWorldSpringBoot {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldSpringBoot.class, args);
	}
}
