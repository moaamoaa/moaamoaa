package com.ssafy.moamoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.TechStack;
import com.ssafy.moamoa.repository.querydsl.SearchTechStackRepository;

public interface TechStackRepository extends JpaRepository<TechStack, Long>, SearchTechStackRepository {

}
