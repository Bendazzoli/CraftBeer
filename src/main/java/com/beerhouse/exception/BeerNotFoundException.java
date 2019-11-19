package com.beerhouse.exception;

public class BeerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8668944240305156355L;
	
	public BeerNotFoundException(String msg) {
		super(msg);
	}
}
