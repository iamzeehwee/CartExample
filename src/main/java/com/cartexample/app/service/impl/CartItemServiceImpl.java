package com.cartexample.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cartexample.app.entity.CartItem;
import com.cartexample.app.repository.CartItemRepository;
import com.cartexample.app.rest.errors.NegativeCartQuantityException;
import com.cartexample.app.service.CartItemService;

@Service
//@Transactional
public class CartItemServiceImpl implements CartItemService {
	
	private final CartItemRepository cartItemRepository;

	public CartItemServiceImpl(CartItemRepository cartItemRepository) {
		this.cartItemRepository = cartItemRepository;
	}
	
	// Retrieve all items in cart
	public List<CartItem> getAllCartItems() {
		return cartItemRepository.findAll();
	}

	// Add 1 item into cart
	public CartItem addCartItem(CartItem item) {
		return cartItemRepository.save(item);
	}

	// Remove 1 item from cart
	public String removeCartItem(int id) {
		cartItemRepository.deleteById(id);
		return "Cart Item delete";
	}
	
	// Update cart item
	public CartItem updateCartItem(CartItem item) {
		CartItem updateItem = cartItemRepository.getOne(item.getId());
		updateItem.setQuantity(item.getQuantity());
		CartItem newlyUpdatedItem = cartItemRepository.save(updateItem);
		System.out.println("Updated " + newlyUpdatedItem);
		return cartItemRepository.save(updateItem);
		
		// Test transaction rollback
		// throw new NegativeCartQuantityException();
	}
}
