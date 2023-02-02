package com.ssafy.moamoa.domain.dto;

import java.util.List;

import com.ssafy.moamoa.domain.entity.Area;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilterDto {
	private List<TechStackCategoryDto> techs;
	private List<Area> areas;

	public FilterDto(List<TechStackCategoryDto> techs, List<Area> areas) {
		this.techs = techs;
		this.areas = areas;
	}
}
