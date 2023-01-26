package com.ssafy.moamoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.moamoa.domain.Project;
import com.ssafy.moamoa.domain.ProjectTechStack;
import com.ssafy.moamoa.repository.querydsl.QProjectTechStackRepository;

public interface ProjectTechStackRepository extends JpaRepository<ProjectTechStack, Long>,
	QProjectTechStackRepository {

	@Query(value = "select projectTechStack " +
		"from ProjectTechStack projectTechStack " +
		"where projectTechStack.project = :project")
	List<ProjectTechStack> findByProject(Project project);
}
