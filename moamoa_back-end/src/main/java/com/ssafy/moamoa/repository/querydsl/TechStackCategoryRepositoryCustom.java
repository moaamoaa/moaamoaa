package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.dto.TechStackCategoryDto;

public interface TechStackCategoryRepositoryCustom {
	List<TechStackCategoryDto> findTechStackWithCategory();
}
