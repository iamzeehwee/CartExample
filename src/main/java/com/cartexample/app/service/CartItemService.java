package com.cartexample.app.service;

import java.util.List;

import com.cartexample.app.entity.CartItem;

public interface CartItemService {

	public List<CartItem> getAllCartItems();
	
	public String addCartItem(CartItem item);
	
	public String removeCartItem(int id);
	
	public String updateCartItem(CartItem item);
	
	public CartItem getOneCartItem(int id);
}
