package com.lms.exceptionHandler;

import java.util.HashMap;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ConstraintViolation {
	 @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getConstraintViolations().forEach(violation -> {
	            String propertyPath = violation.getPropertyPath().toString();
	            String message = violation.getMessage();
	            errors.put(propertyPath, message);
	        });
	        return ResponseEntity.badRequest().body(errors);
	    }
}