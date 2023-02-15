package com.ssafy.moamoa.repository;

import java.util.Optional;

import com.ssafy.moamoa.repository.querydsl.ProjectAreaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.moamoa.domain.entity.Project;
import com.ssafy.moamoa.domain.entity.ProjectArea;
import org.springframework.data.repository.query.Param;

public interface ProjectAreaRepository extends JpaRepository<ProjectArea, Long> , ProjectAreaRepositoryCustom {

	Optional<ProjectArea> findByProject_Id(Long projectId);
}

