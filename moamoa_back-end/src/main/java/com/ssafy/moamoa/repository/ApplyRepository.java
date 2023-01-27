package com.ssafy.moamoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.moamoa.domain.Apply;
import com.ssafy.moamoa.domain.Project;
import com.ssafy.moamoa.domain.User;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
	@Query(value = "select apply "
		+ "from Apply apply "
		+ "where apply.user = :user")
	List<Apply> findByUser(User user);

	@Query(value = "select apply "
		+ "from Apply apply "
		+ "where apply.project = :project")
	List<Apply> findByProject(Project project);
}
