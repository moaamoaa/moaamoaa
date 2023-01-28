package com.ssafy.moamoa.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TechStackForm {
	@JsonProperty("tech_stack_no")
	private Long id;
	@JsonProperty("techName")
	private String name;
	@JsonProperty("img")
	private String img;
}
