package com.ssafy.moamoa.repository;

import com.ssafy.moamoa.repository.querydsl.SearchTechStackRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.TechStack;

public interface TechstackRepository extends JpaRepository<TechStack, Long> , SearchTechStackRepository {

}
