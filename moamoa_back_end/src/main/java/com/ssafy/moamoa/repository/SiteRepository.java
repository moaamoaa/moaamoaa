package com.ssafy.moamoa.repository;

import com.ssafy.moamoa.domain.entity.Site;
import com.ssafy.moamoa.repository.querydsl.SiteRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site,Long>, SiteRepositoryCustom {

}
