package com.ssafy.moamoa.exception.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateUserEmailException extends IllegalStateException {
	public DuplicateUserEmailException(String message) {
		super(message);
	}
}