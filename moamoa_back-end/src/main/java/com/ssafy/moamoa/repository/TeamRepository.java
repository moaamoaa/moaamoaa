package com.ssafy.moamoa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.moamoa.domain.ProjectCategory;
import com.ssafy.moamoa.domain.TeamRole;
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.Team;
import com.ssafy.moamoa.domain.entity.User;

public interface TeamRepository extends JpaRepository<Team, Long> {

	List<Team> findByProject_Id(Long id);

	@Query(value = "select team " +
		"from Team team " +
		"where team.user = :user and team.project = :project")
	Optional<Team> findByUser(User user, Project project);

	Optional<Team> findByRoleAndProject_Id(TeamRole role, Long projectId);

	Optional<Team> findByUser_IdAndProject_Id(Long userId, Long projectId);

	List<Team> findByUser_IdAndProjectCategory(Long userId, ProjectCategory projectCategory);

	List<Team> findByUser_Id(Long id);
}
