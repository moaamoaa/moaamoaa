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
public class SidePjtForm {

	@JsonProperty("name")
	private String name;

	@JsonProperty("year")
	private String year;

	@JsonProperty("context")
	private String context;

}
