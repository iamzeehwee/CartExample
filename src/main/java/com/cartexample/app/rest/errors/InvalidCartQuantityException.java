package com.cartexample.app.rest.errors;

public class InvalidCartQuantityException extends RuntimeException  {

	public InvalidCartQuantityException() {
        super("Invalid cart quantity");
    }
}
