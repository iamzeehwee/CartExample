package com.cartexample.app.service;

import com.cartexample.app.HelloWorldSpringBoot;
import com.cartexample.app.entity.Product;
import com.cartexample.app.repository.CartItemRepository;
import com.cartexample.app.repository.ProductRepository;
import com.cartexample.app.service.impl.CartItemServiceImpl;
import com.cartexample.app.service.impl.ProductServiceImpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductServiceTest {
	ProductService productService;
	ProductRepository productRepository = mock(ProductRepository.class);

	@Before
	public void init() {
		productService = new ProductServiceImpl(productRepository);
	}

	@Test
	public void testRetrieveAllProducts() {
		List<Product> list = new ArrayList<Product>();
		list.add(new Product(1, "Product A", 5));
		list.add(new Product(2, "Product B", 10));
		list.add(new Product(3, "Product C", 20));
		list.add(new Product(4, "Product D", 30));
		
		System.out.println("List Created: " + list.toString());
		when(productRepository.findAll()).thenReturn(list);
		
		List<Product> result = productService.getAllProducts();
		System.out.println("Retrieve from DB: " + result.toString());
		Assert.assertTrue(result.equals(list));
	}

}
