package com.cartexample.app.rest.errors;

public class InvalidCartQuantityException extends RuntimeException  {

	public InvalidCartQuantityException() {
        super("Error: Invalid cart quantity");
    }
}
