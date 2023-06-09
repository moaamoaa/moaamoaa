package com.ssafy.moamoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.entity.SidePjt;
import com.ssafy.moamoa.repository.querydsl.SideProjectRepositoryCustom;

public interface SideProjectRepository
	extends JpaRepository<SidePjt, Long>, SideProjectRepositoryCustom {

}
