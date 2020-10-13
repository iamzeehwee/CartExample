package com.cartexample.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cartexample.app.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	// Do Join fetching to resolve Junit's org.hibernate.LazyInitializationException : 
	// could not initialize proxy – no Session error 
	@Query(value = "SELECT c.* FROM cart_item c, Product p WHERE c.id = ?1", nativeQuery = true)
	public CartItem getOneItem(Integer id);
}
