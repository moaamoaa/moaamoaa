package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.entity.SidePjt;

public interface SideProjectRepositoryCustom {

	List<SidePjt> getSideProjectsByIdAsc(Long profileId);

	SidePjt getSideProjectByAll(Long profileId, SidePjt sidePjt);

	SidePjt getSideProjectById(Long projectId);



}
