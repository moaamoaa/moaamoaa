package com.ssafy.moamoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.moamoa.domain.entity.ProfileArea;
import com.ssafy.moamoa.domain.entity.User;
import com.ssafy.moamoa.repository.projection.AreaOnly;

public interface UserAreaRepository extends JpaRepository<ProfileArea, Long> {
	@Query(value = "select userArea "
		+ "from ProfileArea userArea "
		+ "where userArea.profile = :user")
	List<ProfileArea> findByUser(User user);

	List<AreaOnly> findByProfile_Id(Long id);
}
