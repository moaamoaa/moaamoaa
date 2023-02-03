package com.ssafy.moamoa.repository.querydsl;

import com.ssafy.moamoa.domain.entity.ProjectTechStack;
import com.ssafy.moamoa.domain.entity.SidePjtTechStack;

import java.util.List;

public interface SideProjectTechStackRepositoryCustom {


    List<SidePjtTechStack> getSideProjectsByOrderAsc(Long projectId);

    Long deleteSideProjectTechStackByOrder(int order);

    SidePjtTechStack getSidePjtTechStack(Long projectId, Long techStackId);

    Long deleteAllSideProjectTechStack(Long projectId);


}
