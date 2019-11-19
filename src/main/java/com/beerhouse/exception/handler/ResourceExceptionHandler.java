package com.beerhouse.exception.handler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.beerhouse.exception.BeerAlreadyExistsException;
import com.beerhouse.exception.BeerNotFoundException;
import com.beerhouse.exception.ErrorResponse;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler{
	
	private final static String NOT_FOUND = "NOT_FOUND";
    private final static String CONFLICT = "CONFLICT";
    
	@ExceptionHandler(BeerAlreadyExistsException.class)
	public ResponseEntity<?> handleBeerAlreadyExistsException(BeerAlreadyExistsException exception, HttpServletRequest request){
		List<String> details = new ArrayList<>();
		details.add(exception.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(CONFLICT, details);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(BeerNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleBeerNotFoundException(BeerNotFoundException exception, HttpServletRequest request){
		List<String> details = new ArrayList<>();
		details.add(exception.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(NOT_FOUND, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> "Field: " + x.getField() + ". Message: " + x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);

    }

	
}
