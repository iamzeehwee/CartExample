package com.cartexample.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cartexample.app.entity.CartItem;
import com.cartexample.app.repository.CartItemRepository;
import com.cartexample.app.service.CartItemService;

@Service
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
	public void removeCartItem(int id) {
		cartItemRepository.deleteById(id);
	}
	
	// Update cart item
	public CartItem updateCartItem(CartItem item) {
		CartItem updateItem = cartItemRepository.getOne(item.getId());
		
		if (item.getQuantity() > 0) {
			updateItem.setQuantity(item.getQuantity());
			return cartItemRepository.save(updateItem);
		}
		else if (item.getQuantity() == 0) {
			cartItemRepository.deleteById(item.getId());
			return cartItemRepository.getOneItem(item.getId());
		}
		else {
			return item;
		}
		
		// Test transaction rollback
		// throw new NegativeCartQuantityException();
	}

	public CartItem getOneCartItem(int id) {
		return cartItemRepository.getOneItem(id);
	}
}
