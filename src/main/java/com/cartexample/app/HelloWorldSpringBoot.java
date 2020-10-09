package com.cartexample.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations={"classpath*:context.xml"})
@ComponentScan("com.cartexample.app")
public class HelloWorldSpringBoot {

	public static void main(String[] args) {
		
		Logger logger = LogManager.getLogger(HelloWorldSpringBoot.class);
		
		logger.trace("We've just greeted the user!");
        logger.debug("We've just greeted the user!");
        logger.info("We've just greeted the user!");
        logger.warn("We've just greeted the user!");
        logger.error("We've just greeted the user!");
        logger.fatal("We've just greeted the user!");
		
		SpringApplication.run(HelloWorldSpringBoot.class, args);
	}
}
