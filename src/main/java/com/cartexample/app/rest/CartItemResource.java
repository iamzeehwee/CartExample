package com.cartexample.app.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartexample.app.entity.CartItem;
import com.cartexample.app.service.CartItemService;

@RestController
@RequestMapping("/api")
public class CartItemResource {

	private final CartItemService cartItemService;
	
	public CartItemResource(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}
	
	@GetMapping("/cartItem")
	public List<CartItem> getCartItems() {
        return cartItemService.getAllCartItems();
    }	
	
	@PostMapping("/cartItem/add")
	public CartItem addCartItem(@RequestBody CartItem item) {
		return cartItemService.addCartItem(item);
	}
	
    @DeleteMapping("/cartItem/{id}")
    public String deleteCart(@PathVariable int id) {
		cartItemService.removeCartItem(id);
		return "Cart Item removed";
    }
}
