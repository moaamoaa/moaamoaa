package com.ssafy.moamoa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.ProfileTechStack;
import com.ssafy.moamoa.domain.entity.User;
import com.ssafy.moamoa.repository.querydsl.QProfileRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long>, QProfileRepository {

	Optional<Profile> findByNickname(String nickname);

	Profile findBySearchState(String searchState);

	Optional<Profile> findByUser_Id(Long userId);

	Optional<Profile> findByUser(User user);

	Optional<List<ProfileTechStack>> findByTechStacks(Long userId);
}
