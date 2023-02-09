package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ssafy.moamoa.domain.dto.ProjectResultDto;
import com.ssafy.moamoa.domain.dto.SearchCondition;
import com.ssafy.moamoa.domain.entity.Project;

public interface ProjectRepositoryCustom {
	List<ProjectResultDto> search(SearchCondition condition, Long cursorId, Pageable pageable);

	Project getProjectById(Long projectId);

	void setProjectImgNull(Long projectId);

	void setProjectImgLink(Long projectId, String img);


	}
