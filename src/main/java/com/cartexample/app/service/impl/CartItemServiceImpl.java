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
	public String addCartItem(CartItem item) {
		cartItemRepository.save(item);
		return "Cart item added";
	}

	// Remove 1 item from cart
	public String removeCartItem(int id) {
		cartItemRepository.deleteById(id);
		return "Cart Item removed";
	}
	
	// Update cart item
	public String updateCartItem(CartItem item) {
		CartItem updateItem = cartItemRepository.getOne(item.getId());
		
		if (item.getQuantity() > 0) {
			updateItem.setQuantity(item.getQuantity());
			cartItemRepository.save(updateItem);
			return "Quantity updated to " + item.getQuantity();
		}
		else if (item.getQuantity() == 0) {
			cartItemRepository.deleteById(item.getId());
			return "Cart Item removed";
		}
		else if (item.getQuantity() < 0) {
			return "Negative value not allowed";
		}
		// Cannot test via Postman
		else {
			return "Invalid quantity";
		}
		
		// Test transaction rollback
		// throw new NegativeCartQuantityException();
	}

	public CartItem getOneCartItem(int id) {
		return cartItemRepository.getOne(id);
	}
}
