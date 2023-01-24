package com.ssafy.moamoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.moamoa.domain.Project;
import com.ssafy.moamoa.domain.ProjectTechStack;

public interface ProjectTeckstackRepository extends JpaRepository<ProjectTechStack, Long> {

	@Query(value = "select projectTechStack " +
		"from ProjectTechStack projectTechStack " +
		"where projectTechStack.project = :project")
	List<ProjectTechStack> findByProject(Project project);
}
