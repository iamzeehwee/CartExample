package com.cartexample.app.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartexample.app.entity.Product;
import com.cartexample.app.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductResource {
	
	private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

	@GetMapping("/product")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }
}
