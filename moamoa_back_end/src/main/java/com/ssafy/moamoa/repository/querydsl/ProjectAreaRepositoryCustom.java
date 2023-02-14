package com.ssafy.moamoa.repository.querydsl;

import com.ssafy.moamoa.domain.entity.ProjectArea;

import java.util.List;

public interface ProjectAreaRepositoryCustom {

ProjectArea getProjectAreaById(Long projectId);
}
