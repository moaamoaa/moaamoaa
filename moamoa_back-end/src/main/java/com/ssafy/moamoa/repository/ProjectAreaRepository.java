package com.ssafy.moamoa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.moamoa.domain.Project;
import com.ssafy.moamoa.domain.ProjectArea;

public interface ProjectAreaRepository extends JpaRepository<ProjectArea, Long> {

	@Query(value = "select projectArea " +
		"from ProjectArea projectArea " +
		"where projectArea.project = :project")
	Optional<ProjectArea> findByProject(Project project);
}
