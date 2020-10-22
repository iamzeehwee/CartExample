package com.cartexample.app.batch;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cartexample.app.entity.CartItem;
import com.cartexample.app.repository.CartItemRepository;

@Component
public class CartItemItemWriter implements ItemWriter<CartItem> {
	Logger logger = LogManager.getLogger(CartItemItemWriter.class);

	@Autowired
    private CartItemRepository cartItemRepository;
	
	// ItemWriter accepts a list of CartItems to be inserted
	public void write(List<? extends CartItem> items) throws Exception {
		
		// For each item, execute the insert statement
		for(CartItem item : items) {
			logger.info("Data saved for Item: " + item);
			cartItemRepository.save(item);
		}
	}
}
