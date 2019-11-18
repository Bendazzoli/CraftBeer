package com.beerhouse.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.beerhouse.exception.BeerAlreadyExistsException;
import com.beerhouse.exception.BeerNotFoundExcetpion;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(BeerAlreadyExistsException.class)
	public ResponseEntity<?> handleBeerAlreadyExistsException(BeerAlreadyExistsException e, HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@ExceptionHandler(BeerNotFoundExcetpion.class)
	public ResponseEntity<?> handleBeerNotFoundException(BeerNotFoundExcetpion e, HttpServletRequest request){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
