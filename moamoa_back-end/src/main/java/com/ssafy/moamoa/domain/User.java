package com.ssafy.moamoa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Entity
@Getter
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_no")
	private Long id;

	@NotNull
	@Column(name = "user_email")
	private String email;

	@NotNull
	@Column(name = "user_pwd")
	private String password;

	@Column(name = "user_refresh_token")
	private String refreshToken;

}
