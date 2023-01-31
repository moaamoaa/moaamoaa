package com.ssafy.moamoa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.moamoa.domain.TeamRole;
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.Team;
import com.ssafy.moamoa.domain.entity.User;

public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query(value = "select team " +
		"from Team team " +
		"where team.project = :project")
	List<Team> findByProject(Project project);

	@Query(value = "select team.project " +
		"from Team team " +
		"where team.user = :user")
	List<Project> findByUser(User user);

	Optional<Team> findByRoleAndProject_Id(TeamRole role, Long projectId);
}
