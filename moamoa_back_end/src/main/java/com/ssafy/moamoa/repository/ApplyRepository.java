package com.ssafy.moamoa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.moamoa.domain.entity.Apply;
import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.User;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
	@Query(value = "select apply "
		+ "from Apply apply "
		+ "where apply.user = :user")
	List<Apply> findByUser(@Param("user") User user);

	@Query(value = "select apply "
		+ "from Apply apply "
		+ "where apply.project = :project")
	List<Apply> findByProject(@Param("project") Project project);

	Optional<Apply> findByUser_IdAndProject_Id(Long userId, Long projectId);
}
