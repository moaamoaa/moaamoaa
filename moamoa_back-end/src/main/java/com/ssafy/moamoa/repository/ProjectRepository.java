package com.ssafy.moamoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.moamoa.domain.ProjectCategory;
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.repository.querydsl.ProjectRepositoryCustom;

public interface ProjectRepository extends JpaRepository<Project, Long>, ProjectRepositoryCustom {

	@Query(value = "select project " +
		"from Project project " +
		"where project.category = :category")
	List<Project> findProjectByCategory(@Param("category") ProjectCategory category);
}
