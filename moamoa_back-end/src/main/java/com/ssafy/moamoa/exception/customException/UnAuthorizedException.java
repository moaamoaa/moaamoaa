package com.ssafy.moamoa.exception.customException;

public class UnAuthorizedException extends RuntimeException {
	public UnAuthorizedException(String message) {
		super(message);
	}
}
