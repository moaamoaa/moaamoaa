package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.entity.TechStack;

public interface TechStackRepositoryCustom {

	List<TechStack> searchTechStackByName(String techName);

	TechStack getTechStackById(Long techStackId);

	TechStack getTechStackByName(String techName);
}
