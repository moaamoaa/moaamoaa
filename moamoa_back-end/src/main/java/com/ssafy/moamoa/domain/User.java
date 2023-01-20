package com.ssafy.moamoa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_no")
	private Long id;

	@Email
	@NotNull
	@Column(name = "user_email")
	private String email;

	@NotNull
	@Column(name = "user_pwd")
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
		message = "비밀번호는 8~20자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String password;

	@Column(name = "user_refresh_token")
	private String refreshToken;

	public User() {
	}

	//==set==//
	public void setProfile(Profile profile) {
		profile.setUser(this);
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
