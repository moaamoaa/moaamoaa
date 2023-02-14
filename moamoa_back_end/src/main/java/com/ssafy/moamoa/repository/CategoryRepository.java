package com.ssafy.moamoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
