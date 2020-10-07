package com.cartexample.app.rest.errors;

public class NegativeCartQuantityException extends RuntimeException {

	public NegativeCartQuantityException() {
        super("Negative value not allowed");
    }
}
