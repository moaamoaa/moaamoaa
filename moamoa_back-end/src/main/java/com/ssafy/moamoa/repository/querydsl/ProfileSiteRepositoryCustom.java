package com.ssafy.moamoa.repository.querydsl;

import java.util.List;

import com.ssafy.moamoa.domain.entity.ProfileSite;

public interface ProfileSiteRepositoryCustom {

    ProfileSite getProfileSiteByName(String name);

    List<ProfileSite> getProfileSitesByIdAsc(Long profileId);

    List<ProfileSite> getProfileSitesById(Long profileId);

    void setProfileSiteLink(ProfileSite profileSite);

    Long deleteProfileSiteById(Long profileId);
}
