package com.user.exception.global;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.user.dto.ExceptionResponse;
import com.user.exception.custom.BadCredentialException;
import com.user.exception.custom.UserNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlers {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleUserNotFound(UserNotFoundException userNotFoundException) {
		return new ResponseEntity<>(ExceptionResponse.builder().message(userNotFoundException.getMessage())
				.timestamp(new Timestamp(System.currentTimeMillis())).httpCode("" + HttpStatus.NOT_FOUND).build(),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadCredentialException.class)
	public ResponseEntity<ExceptionResponse> badCredential(BadCredentialException badCredentialException) {
		return new ResponseEntity<>(ExceptionResponse.builder().message(badCredentialException.getMessage())
				.timestamp(new Timestamp(System.currentTimeMillis())).httpCode("" + HttpStatus.BAD_REQUEST).build(),
				HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<Object,Object>> handleConstraintViolationException(ConstraintViolationException ex) {
		Map<Object,Object> errors = new HashMap<>();
		ex.getConstraintViolations().forEach(c->errors.put(c.getPropertyPath(),c.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

}
