package com.ssafy.moamoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.entity.ProfileTechStack;
import com.ssafy.moamoa.repository.projection.TechStackOnly;
import com.ssafy.moamoa.repository.querydsl.ProfileTechStackRepositoryCustom;

public interface ProfileTechStackRepository extends JpaRepository<ProfileTechStack, Long>,
	ProfileTechStackRepositoryCustom {

	List<TechStackOnly> findTop4ByProfile_Id(Long id);
}
