package com.cartexample.app.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ NegativeCartQuantityException.class, InvalidCartQuantityException.class })
	public ResponseEntity<CustomErrorResponse> negativeCartQuantity(Exception ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setStatus(HttpStatus.BAD_REQUEST);
        errors.setMessage(ex.getMessage());
       
        return new ResponseEntity<CustomErrorResponse>(errors, HttpStatus.BAD_REQUEST);
    }
}
