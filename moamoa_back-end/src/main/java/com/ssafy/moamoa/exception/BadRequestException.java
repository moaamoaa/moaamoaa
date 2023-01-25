package com.ssafy.moamoa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // 400번 오류로 반환
public class BadRequestException extends RuntimeException {
	public BadRequestException(String message) {
		super(message); // 부모 클래스에서 전달 받은 메시지 실행
	}
}