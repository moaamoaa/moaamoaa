package com.ssafy.moamoa.repository.querydsl;

import com.ssafy.moamoa.domain.entity.TechStack;

import java.util.List;

public interface TechStackRepositoryCustom {

    List<TechStack> searchTechStackByName(String techName);

    TechStack getTechStackById(Long techStackId);

}
