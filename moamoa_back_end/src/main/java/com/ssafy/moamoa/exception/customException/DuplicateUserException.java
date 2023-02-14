package com.ssafy.moamoa.exception.customException;

public class DuplicateUserException extends IllegalArgumentException {
	public DuplicateUserException(String message) {
		super(message);
	}
}