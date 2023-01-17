package com.ssafy.moamoa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_no")
	private Long id;

	@Column(name = "user_email", length = 100)
	private String email;

	@Setter
	@Column(name = "user_pwd", length = 100)
	private String password;

	@Setter
	@Column(name = "user_refresh_token", length = 100)
	private String refreshToken;

}
