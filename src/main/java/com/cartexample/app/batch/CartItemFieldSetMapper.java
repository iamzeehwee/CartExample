package com.cartexample.app.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.cartexample.app.entity.CartItem;
import com.cartexample.app.entity.Product;

// Once the line mapper has parsed the line into its individual fields, it builds a FieldSet, 
// which contains the named fields, and passes that to the mapFieldSet() method.
public class CartItemFieldSetMapper implements FieldSetMapper<CartItem> {

	Logger logger = LogManager.getLogger(CartItemFieldSetMapper.class);
	
	// Create a CartItem object by reading the values from each line
	public CartItem mapFieldSet(FieldSet fieldSet) throws BindException {
		
		logger.debug("Retrieving prodId from MapFieldSet: " + fieldSet.readInt("prodId"));
		
		CartItem item = new CartItem();
		item.setQuantity(fieldSet.readInt("quantity"));	
		int prodId = fieldSet.readInt("prodId");
		item.setProduct(new Product(prodId));
		return item;
	}

}
