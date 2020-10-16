package com.cartexample.app.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.cartexample.app.HelloWorldSpringBoot;
import com.cartexample.app.entity.CartItem;
import com.cartexample.app.entity.Product;
import com.cartexample.app.rest.CartItemResource;
import com.cartexample.app.rest.ProductResource;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HelloWorldSpringBoot.class)
class ProductControllerTest {
	
	Logger logger = LogManager.getLogger(ProductControllerTest.class);
	
	@Autowired
	private ProductResource productResource;
	
	private MockMvc mockMvc;

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
		mockMvc = MockMvcBuilders.standaloneSetup(productResource).build();
	}

	@AfterEach
	void tearDown() throws Exception {
	} 
	
	@Test
	public void testGetProductResource() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/product"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(jsonPath("$", Matchers.hasSize(4)));
     }
}
