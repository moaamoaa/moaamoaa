package com.ssafy.moamoa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
	private String password;

	@Column(name = "user_refresh_token")
	private String refreshToken;

	public User() {
	}

	//==set==//
	public void setProfile(Profile profile){
		profile.setUser(this);
	}

}
