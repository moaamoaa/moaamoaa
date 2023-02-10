package com.ssafy.moamoa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.moamoa.exception.customException.DuplicateUserException;
import com.ssafy.moamoa.exception.customException.UnAuthorizedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<ErrorResult> duplicateUserExHandle(DuplicateUserException e) {
		log.error("[exceptionHandle] ex", e);
		ErrorResult errorResult = new ErrorResult("409", e.getMessage());
		return new ResponseEntity<>(errorResult, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(UnAuthorizedException.class)
	public ResponseEntity<ErrorResult> unAuthorizedExHandle(UnAuthorizedException e) {
		log.error("[exceptionHandle] ex", e);
		ErrorResult errorResult = new ErrorResult("401", e.getMessage());
		return new ResponseEntity<>(errorResult, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResult> illegalExHandle(IllegalArgumentException e) {
		log.error("[exceptionHandle] ex", e);
		ErrorResult errorResult = new ErrorResult("400", e.getMessage());
		return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
	}

}
