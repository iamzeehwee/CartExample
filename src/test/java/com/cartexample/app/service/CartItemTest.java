package com.cartexample.app.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.cartexample.app.HelloWorldSpringBoot;
import com.cartexample.app.entity.CartItem;
import com.cartexample.app.entity.Product;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HelloWorldSpringBoot.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class CartItemTest {
	
	Logger logger = LoggerFactory.getLogger(CartItemTest.class);
	
	@Autowired
	private CartItemService cartItemService;

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
	@Transactional
	@Rollback(false)
	public void testAddCartItem() {
		CartItem newItem = new CartItem();
		newItem.setQuantity(19);
		newItem.setProduct(new Product(2, "Product B", 10));
		logger.info("Result of testAddCartItem: " + cartItemService.addCartItem(newItem));
		
		List<CartItem> allItems = cartItemService.getAllCartItems();
		logger.info("Result of testRetrieveAllCartItem: " + allItems.size());
		
		Assert.assertTrue("Cart item added".equals(cartItemService.addCartItem(newItem)));
	}
/*
	@Test
	public void testRetrieveAllCartItem() {
		List<CartItem> allItems = cartItemService.getAllCartItems();
		logger.info("Result of testRetrieveAllCartItem: " + allItems.size());
		Assert.assertTrue(allItems.size() > 0);
	} 
	
	@Test
	public void testDeleteCartItem() {
		//System.out.println(cartItemService.removeCartItem(100));
		cartItemService.removeCartItem(100);
	} */
}
