package com.ssafy.moamoa.repository.querydsl;

import com.ssafy.moamoa.domain.entity.ProfileTechStack;

import java.util.List;

public interface ProfileTechStackRepositoryCustom {

    List<ProfileTechStack> getProfileTechStacksByOrderAsc(Long profileId);

    Long deleteAllProfileTechStacksById(Long profileId);

}
