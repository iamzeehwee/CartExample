package com.cartexample.app.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cartexample.app.HelloWorldSpringBoot;
import com.cartexample.app.entity.Product;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HelloWorldSpringBoot.class)
class ProductTest {
	
	Logger logger = LogManager.getLogger(ProductTest.class);
	
	@Autowired
	private ProductService productService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("---Inside setUpBeforeClass---");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp(TestInfo testInfo) throws Exception {
		System.out.println("\nStart..." + testInfo.getDisplayName());
	}

	@AfterEach
	void tearDown() throws Exception {
	} 
	
	@Test
	public void testRetrieveAllProduct() {
		List<Product> allItems = productService.getAllProducts();
		logger.info("Result of testRetrieveAllProduct: " + allItems.size());
		Assertions.assertTrue(allItems.size() > 0);
	}  
}
