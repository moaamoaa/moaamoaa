package com.ssafy.moamoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.TechStack;

public interface TechstackRepository extends JpaRepository<TechStack, Long> {
}
