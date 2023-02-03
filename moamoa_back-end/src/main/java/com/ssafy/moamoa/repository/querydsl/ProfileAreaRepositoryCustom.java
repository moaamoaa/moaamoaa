package com.ssafy.moamoa.repository.querydsl;

import com.ssafy.moamoa.domain.dto.AreaForm;
import com.ssafy.moamoa.domain.entity.ProfileArea;

import java.util.List;

public interface ProfileAreaRepositoryCustom {

    List<ProfileArea> getAreasByIdAsc(Long profileId);

    Long deleteAreasById(Long profileId);
}
