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
	@JsonProperty("senderId")
	private Long senderId;
	@JsonProperty("time")
	private String time;
	@JsonProperty("context")
	private String context;

}
