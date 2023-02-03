package com.ssafy.moamoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.entity.TechStackCategory;
import com.ssafy.moamoa.repository.querydsl.TechStackCategoryRepositoryCustom;

public interface TechStackCategoryRepository extends JpaRepository<TechStackCategory, Long>,
	TechStackCategoryRepositoryCustom {

}
