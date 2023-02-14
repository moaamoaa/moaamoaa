package com.ssafy.moamoa.repository;

import com.ssafy.moamoa.domain.entity.ProfileSite;
import com.ssafy.moamoa.repository.querydsl.ProfileSiteRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileSiteRepository extends JpaRepository<ProfileSite,Long>, ProfileSiteRepositoryCustom {
}
