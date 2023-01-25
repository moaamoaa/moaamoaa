package com.ssafy.moamoa.repository.querydsl;

import com.ssafy.moamoa.domain.TechStack;

import java.util.List;

public interface SearchTechStackRepository {

    List<TechStack> searchTechStackByName(String techName);

    TechStack getTechStackByName(String techName);
}
