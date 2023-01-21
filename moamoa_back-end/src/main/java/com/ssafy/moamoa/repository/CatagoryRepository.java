package com.ssafy.moamoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.Category;

public interface CatagoryRepository extends JpaRepository<Category, Long> {

}
