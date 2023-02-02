package com.ssafy.moamoa.repository;


import com.ssafy.moamoa.domain.entity.SidePjtTechStack;
import com.ssafy.moamoa.repository.querydsl.SideProjectTechStackRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.moamoa.domain.entity.SidePjt;

public interface SideProjectTechStackRepository extends JpaRepository<SidePjtTechStack,Long>, SideProjectTechStackRepositoryCustom {


}
