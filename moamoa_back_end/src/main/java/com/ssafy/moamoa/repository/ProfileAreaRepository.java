package com.ssafy.moamoa.repository;

import com.ssafy.moamoa.domain.entity.ProfileArea;
import com.ssafy.moamoa.repository.querydsl.ProfileAreaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileAreaRepository extends JpaRepository<ProfileArea,Long> , ProfileAreaRepositoryCustom {
}
