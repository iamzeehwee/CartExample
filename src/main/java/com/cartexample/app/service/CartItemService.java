package com.cartexample.app.service;

import java.util.List;

import com.cartexample.app.entity.CartItem;

public interface CartItemService {

	public List<CartItem> getAllCartItems();
	
	public CartItem addCartItem(CartItem item);
	
	public String removeCartItem(int id);
	
	public CartItem updateCartItem(CartItem item);
}
