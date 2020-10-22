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

import com.cartexample.app.HelloWorldSpringBoot;
import com.cartexample.app.entity.CartItem;
import com.cartexample.app.entity.Product;
import com.cartexample.app.filter.CartItemFilter;
import com.cartexample.app.rest.CartItemResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HelloWorldSpringBoot.class)
class CartItemControllerTest {
	
	Logger logger = LogManager.getLogger(CartItemControllerTest.class);
	
	@Autowired
	private CartItemResource cartItemResource;
	
	@Autowired
	private CartItemService cartItemService;
		
	private MockMvc mockMvc;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		System.out.println("---Inside setUpBeforeClass---");

	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	public void setUp(TestInfo testInfo) throws Exception {
		System.out.println("\nStart..." + testInfo.getDisplayName());
		mockMvc = MockMvcBuilders.standaloneSetup(cartItemResource)
				.addFilters(new CartItemFilter())
				.build();
	}

	@AfterEach
	public void tearDown() throws Exception {
	} 
	/*
	@Test
	public void testGetCartItemResource() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/cartItem"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(jsonPath("$[0].quantity", Matchers.is(5)))
			.andExpect(jsonPath("$[0].product.prodName", Matchers.is("Product B")))
			.andExpect(jsonPath("$[0].product.prodPrice", Matchers.is(10.0)));
     }

	@Test
	public void testAddCartItemResource() throws Exception {	
		CartItem newItem = new CartItem(500, new Product(1, "Product A", 5));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/cartItem/add")
			  .content(convertToJsonString(newItem))
			  .contentType(MediaType.APPLICATION_JSON)
		      .accept(MediaType.APPLICATION_JSON))
		      .andExpect(MockMvcResultMatchers.status().isCreated());
     } */
	
	@Test
	public void testUpdateCartItemResource() throws Exception {		

		mockMvc.perform(MockMvcRequestBuilders.put("/api/cartItem")
			  .content("{ \"id\": 509, \"quantity\": 5, \"product\": { \"id\": 4 } }")
			  .contentType(MediaType.APPLICATION_JSON)
		      .accept(MediaType.APPLICATION_JSON))
		      .andExpect(MockMvcResultMatchers.status().isOk())
		      .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", Matchers.is(5)));
	} 
	
	@Test
	public void testDeleteCartItemResource() throws Exception{		
			List<CartItem> allItem = cartItemService.getAllCartItems();
			int idToBeDeleted = allItem.get(allItem.size()-1).getId();
					
			mockMvc.perform(MockMvcRequestBuilders.delete("/api/cartItem/{id}", idToBeDeleted)
				  .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers.status().isOk());
     } 
	
	public String convertToJsonString(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		// Add this to ignore the JsonProperty, else no id will be included
		mapper.disable(MapperFeature.USE_ANNOTATIONS); 
		logger.info("Converting CartItem to JSON String: " + mapper.writeValueAsString(obj));
		return mapper.writeValueAsString(obj);
	}
	
}
