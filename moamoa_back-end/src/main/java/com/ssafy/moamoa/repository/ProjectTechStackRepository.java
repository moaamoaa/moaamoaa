package com.ssafy.moamoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.ProjectTechStack;
import com.ssafy.moamoa.repository.projection.TechStackOnly;
import com.ssafy.moamoa.repository.querydsl.ProjectTechStackRepositoryCustom;

public interface ProjectTechStackRepository extends JpaRepository<ProjectTechStack, Long>,
	ProjectTechStackRepositoryCustom {

	List<TechStackOnly> findByProject_Id(Long id);

	List<TechStackOnly> findTop4ByProject_IdOrderByOrderAsc(Long id);

}
