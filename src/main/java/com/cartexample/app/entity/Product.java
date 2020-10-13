package com.cartexample.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	@JsonIgnore // Add this to hide the id in response
	private int id;
	
	@NotNull
	@Column(name="prodName")
	private String prodName;
	
	@NotNull
	@Column(name="prodPrice")
	private float prodPrice;
	
	@OneToMany(mappedBy = "product", cascade=CascadeType.ALL, orphanRemoval=true)
	// Add this annotation to resolve Jackson infinite recursion due to bidirectional relationship
	@JsonBackReference
	public List<CartItem> cartItems;
	
	public Product() {
	}
	
	public Product(String prodName, float prodPrice) {
		this.prodName = prodName;
		this.prodPrice = prodPrice;
	}
	
	public Product(int id, String prodName, float prodPrice) {
		this.id = id;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
	}

	@JsonIgnore // Add this for hiding Id in response
	public int getId() {
		return id;
	}

	@JsonProperty // Add this for hiding Id in response
	public void setId(int id) {
		this.id = id;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public float getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(float prodPrice) {
		this.prodPrice = prodPrice;
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", prodName=" + prodName + ", prodPrice=" + prodPrice + "]";
	}
	
}
