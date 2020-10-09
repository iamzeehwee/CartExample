package com.cartexample.app.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.tools.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cartexample.app.HelloWorldSpringBoot;
import com.cartexample.app.entity.CartItem;
import com.cartexample.app.entity.Product;
import com.cartexample.app.repository.CartItemRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HelloWorldSpringBoot.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class CartItemTest {
	
	Logger logger = LogManager.getLogger(CartItemTest.class);
	
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
	/*
	@Test
	@Rollback(false)
	public void testAddCartItem() {
		
		CartItem newItem = new CartItem();
		newItem.setQuantity(19);
		newItem.setProduct(new Product(2, "Product B", 10));
		logger.info("Print CartItem: " + newItem);
		logger.info("Result of testAddCartItem: " + cartItemService.addCartItem(newItem));
		
		List<CartItem> items = cartItemService.getAllCartItems();
		logger.info("Result of testRetrieveAllCartItem: " + items.size());
		
		Assertions.assertTrue("Cart item added".equals(cartItemService.addCartItem(newItem)));
	}
	
	
	@Test
	public void testRetrieveAllCartItem() {
		List<CartItem> allItems = cartItemService.getAllCartItems();
		logger.info("Result of testRetrieveAllCartItem: " + allItems.size());
		Assertions.assertTrue(allItems.size() > 0);
	}  */
	
	
	@Test
	public void testDeleteCartItem() {
		List<CartItem> allItems = cartItemService.getAllCartItems();
		int idToDelete = allItems.get(allItems.size() - 1).getId();		
		logger.info("Retriving Cart Item to be deleted: " + allItems.get(allItems.size() - 1));
		logger.info("Deleting Cart Item with ID: " + idToDelete);
		Assertions.assertTrue("Cart Item deleted".equals(cartItemService.removeCartItem(idToDelete)));
	} 
}
