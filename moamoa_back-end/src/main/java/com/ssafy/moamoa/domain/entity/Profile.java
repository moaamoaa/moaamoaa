package com.ssafy.moamoa.domain.entity;

import com.ssafy.moamoa.domain.ProfileOnOffStatus;
import com.ssafy.moamoa.domain.ProfileSearchStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
public class Profile {
	@Id
	@GeneratedValue
	@Column(name = "profile_no")
	private Long id;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_no")
	private User user;

	@NotNull
	@Column(name = "profile_nickname")
	private String nickname;

	@NotNull
	@ColumnDefault("'ALL'")
	@Enumerated(EnumType.STRING)
	private ProfileSearchStatus searchState = ProfileSearchStatus.ALL;

	@NotNull
	@ColumnDefault("'ONLINE'")
	@Enumerated(EnumType.STRING)
	private ProfileOnOffStatus profileOnOffStatus = ProfileOnOffStatus.ONLINE;

	@Column(name = "profile_img")
	private String img;

	@Lob
	@Column(name = "profile_context", columnDefinition = "TEXT")
	private String context;

	// @OneToMany(mappedBy = "profile_stack_no")
	// private List<ProfileTechStack> profileTechStack;
	//
	// @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
	// private List<ProfileArea> profileAreas = new ArrayList<>();
	@Column(name="profile_cnt_offer")
	@ColumnDefault("0")
	private int countOffer;

	@Column(name="profile_hit")
	@ColumnDefault("0")
	private int hit;

	public Profile() {
	}

	//==set==//
	public void setUser(User newUser) {
		this.user = newUser;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setSearchState(ProfileSearchStatus profileSearchStatus) {
		this.searchState = profileSearchStatus;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public void setProfileOnOffStatus(ProfileOnOffStatus profileOnOffStatus) {
		this.profileOnOffStatus = profileOnOffStatus;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setCountOffer(int countOffer){this.countOffer = countOffer;}
}