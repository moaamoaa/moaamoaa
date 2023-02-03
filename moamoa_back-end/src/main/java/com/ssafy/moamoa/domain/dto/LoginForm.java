package com.ssafy.moamoa.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;

@Getter
public class LoginForm {
	@Email
	@NotEmpty
	private String email;

	private String password;

}
