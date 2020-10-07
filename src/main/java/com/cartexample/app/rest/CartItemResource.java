package com.cartexample.app.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartexample.app.entity.CartItem;
import com.cartexample.app.rest.errors.InvalidCartQuantityException;
import com.cartexample.app.rest.errors.NegativeCartQuantityException;
import com.cartexample.app.service.CartItemService;

@RestController
@RequestMapping("/api")
public class CartItemResource {

	private final CartItemService cartItemService;
	
	public CartItemResource(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}
	
	@GetMapping("/cartItem")
	public ResponseEntity<List<CartItem>> getCartItems() {
        List<CartItem> items = cartItemService.getAllCartItems();
		return ResponseEntity.ok().body(items);
    }	
	
	@PostMapping("/cartItem/add")
	public ResponseEntity<CartItem> addCartItem(@RequestBody CartItem item) {
		CartItem cartItem = cartItemService.addCartItem(item);
		return ResponseEntity.ok().body(cartItem);
	}
	
    @DeleteMapping("/cartItem/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable int id) {
    	cartItemService.removeCartItem(id);
    	return new ResponseEntity<String>("Cart Item deleted", HttpStatus.OK);
    }
    
	@PutMapping("/cartItem")
	// Use <?> instead of CartItem, used as WildCard
	public ResponseEntity<?> updateCartItem(@RequestBody CartItem item) {
		if (item.getQuantity() > 0) {
			cartItemService.updateCartItem(item);
			return new ResponseEntity<>("Quantity updated to " + item.getQuantity(), HttpStatus.OK);
		}
		else if (item.getQuantity() == 0) {
			cartItemService.removeCartItem(item.getId());
			return new ResponseEntity<>("Cart Item deleted", HttpStatus.OK);
		}
		else if (item.getQuantity() < 0) {
			throw new NegativeCartQuantityException();
		}
		// Cannot test via Postman
		else {
			throw new InvalidCartQuantityException();
		}
	}
}
