package com.beerhouse.exception;

public class BeerAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 701090898783390713L;
	
	public BeerAlreadyExistsException(String msg) {
		super(msg);
	}
}
