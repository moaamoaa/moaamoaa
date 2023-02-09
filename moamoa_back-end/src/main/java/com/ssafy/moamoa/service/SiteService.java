package com.ssafy.moamoa.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.dto.SiteForm;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.ProfileSite;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.ProfileSiteRepository;
import com.ssafy.moamoa.repository.SiteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
@Slf4j
public class SiteService {

    @PersistenceContext
    EntityManager em;

    private final ProfileRepository profileRepository;

    private final SiteRepository siteRepository;

    private final ProfileSiteRepository profileSiteRepository;

    public List<SiteForm> getProfileSites(Long profileId)
    {
        List<ProfileSite> profileSiteList = profileSiteRepository.getProfileSitesByIdAsc(profileId);
        List<SiteForm> siteFormList = new ArrayList<>();
        for(ProfileSite profileSite : profileSiteList)
        {
        SiteForm siteForm = SiteForm.builder()
                .name(profileSite.getSite().getName())
                .link(profileSite.getLink()).build();

        siteFormList.add(siteForm);
        }
        return siteFormList;
    }

    public List<SiteForm> modifyProfileSite(Long profileId, List<SiteForm> siteFormList)
    {
        Profile profile = profileRepository.getProfileById(profileId);

        Long count = profileSiteRepository.deleteProfileSiteById(profileId);

        for(SiteForm siteForm : siteFormList)
        {
            ProfileSite profileSite = ProfileSite.builder()
                    .profile(profile)
                    .site(siteRepository.getSiteByName(siteForm.getName()))
                    .link(siteForm.getLink())
                    .build();

             profileSiteRepository.save(profileSite);

        }

        return siteFormList;
    }

}
