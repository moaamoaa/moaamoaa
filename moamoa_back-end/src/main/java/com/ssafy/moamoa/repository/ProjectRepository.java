package com.ssafy.moamoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.moamoa.domain.Project;
import com.ssafy.moamoa.domain.ProjectCategory;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Query(value = "select project " +
		"from Project project " +
		"where project.category = :category")
	List<Project> findProject(@Param("category") ProjectCategory category);
}
