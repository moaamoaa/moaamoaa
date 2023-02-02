package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.entity.SidePjt;

public interface QSideProjectRepository {

	List<SidePjt> getSideProjects(Long profileId);
}
