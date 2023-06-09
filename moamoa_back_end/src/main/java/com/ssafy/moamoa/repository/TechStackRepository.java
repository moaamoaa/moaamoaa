package com.ssafy.moamoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.entity.TechStack;
import com.ssafy.moamoa.repository.querydsl.TechStackRepositoryCustom;

public interface TechStackRepository extends JpaRepository<TechStack, Long>, TechStackRepositoryCustom {

}
