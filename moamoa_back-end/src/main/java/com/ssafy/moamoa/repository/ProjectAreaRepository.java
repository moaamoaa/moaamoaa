package com.ssafy.moamoa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.ProjectArea;
import org.springframework.data.repository.query.Param;

public interface ProjectAreaRepository extends JpaRepository<ProjectArea, Long> {

	@Query(value = "select projectArea " +
		"from ProjectArea projectArea " +
		"where projectArea.project = :project")
	Optional<ProjectArea> findByProject(@Param("project") Project project);
}
