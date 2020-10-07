package com.cartexample.app.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cartexample.app.entity.Product;
import com.cartexample.app.repository.ProductRepository;
import com.cartexample.app.service.ProductService;

@Service
//@Transactional
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

}
