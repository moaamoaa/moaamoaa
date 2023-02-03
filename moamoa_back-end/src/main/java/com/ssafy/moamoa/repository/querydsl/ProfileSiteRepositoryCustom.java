package com.ssafy.moamoa.repository.querydsl;

import com.ssafy.moamoa.domain.entity.ProfileSite;

import java.util.List;
import java.util.Optional;

public interface ProfileSiteRepositoryCustom {

    ProfileSite getProfileSiteByName(String name);

    List<ProfileSite> getProfileSitesById(Long profileId);

    void setProfileSiteLink(ProfileSite profileSite);

    Long deleteProfileSiteById(Long profileId);
}
