package com.ssafy.moamoa.repository.querydsl;

import com.ssafy.moamoa.domain.entity.ProfileSite;

import java.util.Optional;

public interface ProfileSiteRepositoryCustom {

    ProfileSite getProfileSiteByName(String name);

    void setProfileSiteLink(ProfileSite profileSite);

    Long deleteProfileSiteById(Long profileId);
}
