package com.cartexample.app.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cartexample.app.entity.CartItem;
import com.cartexample.app.rest.errors.InvalidCartQuantityException;
import com.cartexample.app.rest.errors.NegativeCartQuantityException;
import com.cartexample.app.service.CartItemService;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CartItemResource {

	private final CartItemService cartItemService;
	
	Logger logger = LoggerFactory.getLogger(CartItemResource.class);
	
	public CartItemResource(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}
	
	@GetMapping("/cartItem")
	public ResponseEntity<List<CartItem>> getCartItems() {
        List<CartItem> items = cartItemService.getAllCartItems();
		return ResponseEntity.ok().body(items);
    }	
	
	@RequestMapping(path = "/cartItem/add", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE})
	public ResponseEntity<CartItem> addCartItem(@RequestBody CartItem item) throws URISyntaxException {
		CartItem cartItem = cartItemService.addCartItem(item);
		return ResponseEntity.created(new URI("/api/cartItem/" + cartItem.getId())).body(item);
	} 
	
    @DeleteMapping("/cartItem/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable int id) {
    	cartItemService.removeCartItem(id);
    	return new ResponseEntity<Void>(HttpStatus.OK);
    } 
    
	@RequestMapping(path = "/cartItem", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE} )
	// Use <?> instead of CartItem, used as WildCard
	public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem item) {
		item = cartItemService.updateCartItem(item);
		return ResponseEntity.ok().body(item);
	}
	
	@GetMapping("/cartItem/{id}")
	public ResponseEntity<CartItem> getOneCartItem(@PathVariable int id) {
		return ResponseEntity.ok().body(cartItemService.getOneCartItem(id));
    }	
}
