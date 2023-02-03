package com.ssafy.moamoa.repository;

import java.util.List;
import java.util.Optional;

import com.ssafy.moamoa.repository.querydsl.AreaRepositoryCustom;
import com.ssafy.moamoa.repository.querydsl.ProfileAreaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.entity.Area;

public interface AreaRepository extends JpaRepository<Area, Long>, AreaRepositoryCustom {
	@Override
	Optional<Area> findById(Long aLong);

	List<Area> findAll();
}
