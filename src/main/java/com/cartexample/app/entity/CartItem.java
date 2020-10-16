package com.cartexample.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="cart_item")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	@JsonIgnore() // Add this to hide the id in response
	private int id;
	
	@NotNull
	@Column(name="quantity")
	private int quantity;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="product_id")
	// Add this annotation to resolve Jackson infinite recursion due to bidirectional relationship
	// Comment off to resolve "Content type 'application/json;charset=UTF-8' not supported"
	//@JsonManagedReference
	private Product product;
	
	public CartItem() {
		
	}

	public CartItem(int quantity, Product product) {
		this.quantity = quantity;
		this.product = product;
	}
	
	public CartItem(int id, int quantity, Product product) {
		this.id = id;
		this.quantity = quantity;
		this.product = product;
	}

	@JsonIgnore // Add this for hiding Id in response
	public int getId() {
		return id;
	}

	@JsonProperty // Add this for hiding Id in response
	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", quantity=" + quantity + ", product=" + product + "]";
	}
}
