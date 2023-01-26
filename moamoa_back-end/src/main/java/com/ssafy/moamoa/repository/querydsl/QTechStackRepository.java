package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.TechStack;

public interface QTechStackRepository {

	List<TechStack> searchTechStackByName(String techName);

	TechStack getTechStackById(Long techStackId);

	TechStack getTechStackByName(String techName);
}
