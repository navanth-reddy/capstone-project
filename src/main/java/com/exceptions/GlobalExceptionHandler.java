package com.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice  // Enables centralized exception handling for all REST controllers.
public class GlobalExceptionHandler {
	 // Handles validation exceptions for @Valid annotated DTOs
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<String> details = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			details.add(fieldName + "|" + errorMessage);
		});
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
	}

	 // Handles custom exception for resource not found (e.g., blog not found)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("message", ex.getMessage());
		response.put("status", HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	// Handles InvalidBlogIdException when an invalid blog ID is provided
	@ExceptionHandler(InvalidBlogIdException.class)
    public ResponseEntity<String> handleInvalidBlogIdException(InvalidBlogIdException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}