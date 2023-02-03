package com.ssafy.moamoa.domain.dto;

import java.util.ArrayList;
import java.util.List;

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

	@JsonProperty("pjt_tech_stack")
	private List<TechStackForm> techStackFormList = new ArrayList<>();

}
