package com.ssafy.moamoa.domain.dto;

import java.util.List;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.moamoa.domain.entity.Category;
import com.ssafy.moamoa.domain.entity.TechStack;

import lombok.Data;

@Data
public class TechStackCategoryDto {
	private Category category;
	private List<TechStack> techStacks;
	
	@QueryProjection
	public TechStackCategoryDto(Category category, List<TechStack> techStacks) {
		this.category = category;
		this.techStacks = techStacks;
	}
}
