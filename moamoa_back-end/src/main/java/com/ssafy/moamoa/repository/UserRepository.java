package com.ssafy.moamoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByEmail(String email);
}
