package com.cartexample.app.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cartexample.app.entity.CartItem;
import com.cartexample.app.entity.Product;
import com.cartexample.app.repository.CartItemRepository;
import com.cartexample.app.service.impl.CartItemServiceImpl;

public class CartItemServiceTest {

	CartItemService cartItemService;
	CartItemRepository cartItemRepository = mock(CartItemRepository.class);
	
	@Before
	public void init() {
		cartItemService = new CartItemServiceImpl(cartItemRepository);
	} 
	/*
	@Test
	public void testRetrieveAllCartItems() {
		List<CartItem> list = new ArrayList<CartItem>();
		list.add(new CartItem(1, 5, new Product(2, "Product B", 10)));
		list.add(new CartItem(3, 19, new Product(3, "Product C", 20)));
		list.add(new CartItem(36, 15, new Product(1, "Product A", 5)));
		list.add(new CartItem(68, 11, new Product(3, "Product C", 20)));
		
		System.out.println("\nList Created: " + list.toString());
		when(cartItemRepository.findAll()).thenReturn(list);
		
		List<CartItem> result =  new ArrayList<CartItem>();
		result = cartItemService.getAllCartItems();
		System.out.println("Retrieve from DB: " + result.toString());
		Assert.assertTrue(result.equals(list));
	}
	*/
	@Test
	public void testAddCartItem() {
		CartItem item = new CartItem(1, 13, new Product(2, "Product B", 10));
		when(cartItemRepository.save(item)).thenReturn(item);
		System.out.println("\nCart Item Added: " + item);
		Assert.assertEquals(item, cartItemRepository.save(item));
	}
	/*
	@Test
	public void testRemoveCartItem() {
		cartItemService.removeCartItem(1);
		verify(cartItemRepository, times(1)).deleteById(1);
	}*/
}
