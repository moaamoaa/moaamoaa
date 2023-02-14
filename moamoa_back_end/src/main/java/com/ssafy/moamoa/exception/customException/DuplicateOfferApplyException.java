package com.ssafy.moamoa.exception.customException;

public class DuplicateOfferApplyException extends IllegalArgumentException {
	public DuplicateOfferApplyException(String message) {
		super(message);
	}
}