package com.ssafy.moamoa.repository.querydsl;

import com.ssafy.moamoa.domain.entity.SidePjt;

import java.util.List;

public interface SideProjectRepositoryCustom {

	List<SidePjt> getSideProjectsByIdAsc(Long profileId);


	SidePjt getSideProjectById(Long projectId);

	Long deleteSideProjectById(Long projectId);

}
