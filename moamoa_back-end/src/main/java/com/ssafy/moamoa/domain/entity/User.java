package com.ssafy.moamoa.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

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
	private String password;

	@Column(name = "user_refresh_token")
	private String refreshToken;

	@Column(name = "user_join_date")
	@NotNull
	private LocalDate joinDate;

	@NotNull
	@ColumnDefault("0")
	@Column(name = "user_state")
	private boolean isLocked = false;

	public User() {
	}

	//==비즈니스 로직==//

	public void setProfile(Profile profile) {
		profile.setUser(this);
	}

	public void setLocked(boolean locked) {
		isLocked = locked;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLocked(boolean isLocked){this.isLocked = isLocked;}

	public void deleteRefreshToken() {
		this.refreshToken = null;
	}

	public void saveRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public boolean isLocked() {
		return isLocked;
	}
}
