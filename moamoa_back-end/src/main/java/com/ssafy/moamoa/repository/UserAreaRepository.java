package com.ssafy.moamoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.moamoa.domain.User;
import com.ssafy.moamoa.domain.UserArea;

public interface UserAreaRepository extends JpaRepository<UserArea, Long> {
	@Query(value = "select userArea "
		+ "from UserArea userArea "
		+ "where userArea.user = :user")
	List<UserArea> findByUser(User user);
}
