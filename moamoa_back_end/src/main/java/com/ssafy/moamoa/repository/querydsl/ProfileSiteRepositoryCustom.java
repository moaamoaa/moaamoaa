package com.ssafy.moamoa.repository.querydsl;

import com.ssafy.moamoa.domain.entity.ProfileSite;

import java.util.List;

public interface ProfileSiteRepositoryCustom {


    List<ProfileSite> getProfileSitesByIdAsc(Long profileId);


    Long deleteProfileSiteById(Long profileId);
}
