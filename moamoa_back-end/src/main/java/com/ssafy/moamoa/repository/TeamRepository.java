package com.ssafy.moamoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.moamoa.domain.Project;
import com.ssafy.moamoa.domain.Team;
import com.ssafy.moamoa.domain.User;

public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query(value = "select team " +
		"from Team team " +
		"where team.project = :project")
	List<Team> findByProject(Project project);

	@Query(value = "select team.project " +
		"from Team team " +
		"where team.user = :user")
	List<Project> findByProject(User user);
}
