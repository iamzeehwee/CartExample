package com.cartexample.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cartexample.app.entity.CartItem;
import com.cartexample.app.repository.CartItemRepository;
import com.cartexample.app.service.CartItemService;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {
	
	private final CartItemRepository cartItemRepository;

	public CartItemServiceImpl(CartItemRepository cartItemRepository) {
		this.cartItemRepository = cartItemRepository;
	}
	
	public List<CartItem> getAllCartItems() {
		return cartItemRepository.findAll();
	}

	public CartItem addCartItem(CartItem item) {
		return cartItemRepository.save(item);
	}

	public String removeCartItem(int id) {
		cartItemRepository.deleteById(id);
		return "Cart Item removed";
	}
}
