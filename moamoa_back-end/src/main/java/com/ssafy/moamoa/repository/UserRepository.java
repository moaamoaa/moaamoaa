package com.ssafy.moamoa.repository;

import java.util.Optional;

import com.ssafy.moamoa.repository.querydsl.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> , UserRepositoryCustom {

	Optional<User> findByEmail(String email);

	void deleteByEmail(String email);
}
