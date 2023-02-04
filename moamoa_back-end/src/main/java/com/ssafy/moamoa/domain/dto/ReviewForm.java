package com.ssafy.moamoa.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewForm {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("profileId")
	private Long profileId;

	// 프로필 아이디
	@JsonProperty("senderId")
	private Long senderId;

	@JsonProperty("img")
	private String img;

	@JsonProperty("sender")
	private String name;

	@JsonProperty("time")
	private String time;

	@JsonProperty("context")
	private String context;

}
