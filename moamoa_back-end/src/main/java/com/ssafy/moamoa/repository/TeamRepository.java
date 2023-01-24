package com.ssafy.moamoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.moamoa.domain.Project;
import com.ssafy.moamoa.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query(value = "select team " +
		"from Team team " +
		"where team.project = :project")
	List<Team> findByProject(Project project);
}
