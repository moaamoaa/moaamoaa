package com.ssafy.moamoa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.User;
import com.ssafy.moamoa.repository.querydsl.ProfileRepositoryCustom;

public interface ProfileRepository extends JpaRepository<Profile, Long>, ProfileRepositoryCustom {

	Optional<Profile> findByNickname(String nickname);

	Profile findBySearchState(String searchState);

	Optional<Profile> findByUser_Id(Long userId);

	Optional<Profile> findByUser(User user);
}
