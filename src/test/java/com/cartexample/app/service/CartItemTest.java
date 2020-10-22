package com.cartexample.app.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.cartexample.app.HelloWorldSpringBoot;
import com.cartexample.app.entity.CartItem;
import com.cartexample.app.entity.Product;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HelloWorldSpringBoot.class)
//@AutoConfigureTestDatabase(replace=Replace.NONE)
//@Transactional(propagation = Propagation.REQUIRES_NEW)
//@EnableTransactionManagement
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
	
	@Test
	public void testAddCartItem() {
		List<CartItem> items = cartItemService.getAllCartItems();
		int initialCount = items.size();
		logger.info("Total number of cart items: " + initialCount);
		
		CartItem newItem = new CartItem();
		newItem.setQuantity(19);
		newItem.setProduct(new Product(2, "Product B", 10));
		logger.info("Print CartItem: " + newItem);
		logger.info("Result of testAddCartItem: " + cartItemService.addCartItem(newItem));
		
		items = cartItemService.getAllCartItems();
		logger.info("Total number of cart items after insertion: " + items.size());
		Assertions.assertTrue(initialCount+1 == items.size());
	}
	/*
	@Test
	public void testRetrieveAllCartItem() {
		List<CartItem> allItems = cartItemService.getAllCartItems();
		logger.info("Result of testRetrieveAllCartItem: " + allItems.size());
		Assertions.assertTrue(allItems.size() > 0);
	}  
	
	@Test
	public void testDeleteCartItem() {
		CartItem newItem = new CartItem();
		newItem.setQuantity(19);
		newItem.setProduct(new Product(3, "Product C", 15));
		logger.info("Print CartItem: " + newItem);
		logger.info("Result of testAddCartItem: " + cartItemService.addCartItem(newItem));
		
		List<CartItem> allItems = cartItemService.getAllCartItems();
		int idToDelete = allItems.get(allItems.size() - 1).getId();		
		logger.info("Retriving Cart Item to be deleted: " + idToDelete);
		logger.info("Deleting Cart Item with ID: " + idToDelete);
		cartItemService.removeCartItem(idToDelete);

		Assertions.assertNull(cartItemService.getOneCartItem(idToDelete));
	} */
	/*
	@Test
	public void testValidUpdateCartItem() {
		List<CartItem> allItems = cartItemService.getAllCartItems();
		CartItem item = allItems.get(allItems.size() - 1);		
		logger.info("Retriving Cart Item to be updated: " + item);
		item.setQuantity(100);
		String result = cartItemService.updateCartItem(item);
		logger.info("Result for testValidUpdateCartItem: " + result);
		Assertions.assertTrue("Quantity updated to 100".equals(result));
	} 
	
	@Test
	public void testUpdateCartItemQuantityToNegative() {
		List<CartItem> allItems = cartItemService.getAllCartItems();
		CartItem item = allItems.get(allItems.size() - 1);		
		logger.info("Retriving Cart Item to be updated: " + item);
		item.setQuantity(-10);
		String result = cartItemService.updateCartItem(item);
		logger.info("Result for testUpdateCartItemQuantityToNegative: " + result);
		Assertions.assertTrue("Negative value not allowed".equals(result));
	}
	
	@Test
	public void testUpdateCartItemQuantityToZero() {
		List<CartItem> allItems = cartItemService.getAllCartItems();
		CartItem item = allItems.get(allItems.size() - 1);		
		logger.info("Retriving Cart Item to be updated: " + item);
		item.setQuantity(0);
		String result = cartItemService.updateCartItem(item);
		logger.info("Result for testUpdateCartItemQuantityToZero: " + result);
		Assertions.assertTrue("Cart Item removed".equals(result));
	} */
}
