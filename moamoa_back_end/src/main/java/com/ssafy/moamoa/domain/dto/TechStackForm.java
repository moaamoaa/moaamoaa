package com.ssafy.moamoa.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.moamoa.domain.entity.TechStack;

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
	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("logo")
	private String logo;

	public static TechStackForm toEntity(TechStack techStack){
		return TechStackForm.builder()
			.id(techStack.getId())
			.name(techStack.getName())
			.logo(techStack.getLogo())
			.build();
	}
}
