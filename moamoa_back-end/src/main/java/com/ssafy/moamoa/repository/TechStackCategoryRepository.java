package com.ssafy.moamoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.entity.TechStackCategory;

public interface TechStackCategoryRepository extends JpaRepository<TechStackCategory, Long> {

}
