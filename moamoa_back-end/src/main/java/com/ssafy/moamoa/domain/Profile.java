package com.ssafy.moamoa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Profile {
	@Id
	@Column(name = "profile_no")
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_no")
	private User user;

	@Setter
	@Column(name = "profile_nickname", length = 100)
	private String nickname;

	@Setter
	@Column(name = "profile_search_state")
	@Enumerated(EnumType.STRING)
	private ProfileSearchStatus searchState;

	@Setter
	@Lob
	@Column(name = "profile_img", columnDefinition = "BLOB")
	private byte[] img;

	@Setter
	@Lob
	@Column(name = "profile_context", length = 1000)
	private String context;

}
