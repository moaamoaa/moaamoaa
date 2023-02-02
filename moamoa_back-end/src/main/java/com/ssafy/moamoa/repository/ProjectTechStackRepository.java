package com.ssafy.moamoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.ProjectTechStack;
import com.ssafy.moamoa.repository.projection.TechStackOnly;
import com.ssafy.moamoa.repository.querydsl.ProjectTechStackRepositoryCustom;

public interface ProjectTechStackRepository extends JpaRepository<ProjectTechStack, Long>,
	ProjectTechStackRepositoryCustom
	 {

	@Query(value = "select projectTechStack " +
		"from ProjectTechStack projectTechStack " +
		"where projectTechStack.project = :project")
	List<ProjectTechStack> findByProject(Project project);

	List<TechStackOnly> findTop4ByProject_Id(Long id);

}
