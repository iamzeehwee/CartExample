package com.cartexample.app.rest.errors;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cartexample.app.filter.CartItemFilter;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ NegativeCartQuantityException.class, InvalidCartQuantityException.class, ConstraintViolationException.class })
	public ResponseEntity<CustomErrorResponse> handleCartException(Exception ex) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setStatus(HttpStatus.OK);
        errors.setMessage(ex.getMessage());
               
        return new ResponseEntity<CustomErrorResponse>(errors, HttpStatus.OK);
    }
	
	@ExceptionHandler
	public ResponseEntity<CustomErrorResponse> handleAllException(Exception ex) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setStatus(HttpStatus.OK);
        errors.setMessage("Error: " + ex.getMessage());
       
        return new ResponseEntity<CustomErrorResponse>(errors, HttpStatus.OK);
    }
}
