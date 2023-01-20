package com.ssafy.moamoa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
}
