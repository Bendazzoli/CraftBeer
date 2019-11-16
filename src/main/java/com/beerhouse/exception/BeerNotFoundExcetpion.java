package com.beerhouse.exception;

public class BeerNotFoundExcetpion extends RuntimeException {

	private static final long serialVersionUID = 8668944240305156355L;
	
	public BeerNotFoundExcetpion(String msg) {
		super(msg);
	}
}
