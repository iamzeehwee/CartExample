package com.cartexample.app.service;

import java.util.List;

import com.cartexample.app.entity.Product;

public interface ProductService {
	
	// Get entire list of product 
	public List<Product> getAllProducts();
	
	// Get one product
	//public Product getProduct(int id);
}
