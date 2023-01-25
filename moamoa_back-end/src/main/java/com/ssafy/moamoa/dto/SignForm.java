package com.ssafy.moamoa.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignForm {

	@Email
	private String email;

	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*?[0-9])(?=.*[\\{\\}\\[\\]\\/?.,;:|\\)*~`!^\\-_+<>@\\#$%&\\\\\\=\\(\\'\\\"]).{8,20}$",
		message = "비밀번호는 8~20자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String password;

	@NotNull
	private String nickname;
}
