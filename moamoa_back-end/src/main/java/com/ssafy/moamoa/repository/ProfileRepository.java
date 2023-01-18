package com.ssafy.moamoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

	List<Profile> findByNickname(String nickname);
}
