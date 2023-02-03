package com.ssafy.moamoa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateProfileNicknameException extends IllegalStateException{ public DuplicateProfileNicknameException(String message){super(message);}
}