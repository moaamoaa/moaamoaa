package com.ssafy.moamoa.service;

import com.ssafy.moamoa.domain.dto.SiteForm;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.ProfileSite;
import com.ssafy.moamoa.domain.entity.Site;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.ProfileSiteRepository;
import com.ssafy.moamoa.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

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

    public List<SiteForm> getProfileSites(Long profileId) {
        List<ProfileSite> profileSiteList = profileSiteRepository.getProfileSitesByIdAsc(profileId);
        List<SiteForm> siteFormList = new ArrayList<>();
        for (ProfileSite profileSite : profileSiteList) {
            SiteForm siteForm = SiteForm.builder()
                    .name(profileSite.getSite().getName())
                    .logo(profileSite.getSite().getLogo())
                    .link(profileSite.getLink()).build();

            siteFormList.add(siteForm);
        }
        return siteFormList;
    }

    public List<SiteForm> modifyProfileSite(Long profileId, List<SiteForm> siteFormList) {
        Profile profile = profileRepository.getProfileById(profileId);
        List<SiteForm> returnList = new ArrayList<>();
        Long count = profileSiteRepository.deleteProfileSiteById(profileId);

        for (SiteForm siteForm : siteFormList) {
            Site site = siteRepository.getSiteByName(siteForm.getName());
            ProfileSite profileSite = ProfileSite.builder()
                    .profile(profile)
                    .site(site)
                    .link(siteForm.getLink())
                    .build();

            SiteForm result = SiteForm.builder()
                    .name(siteForm.getName())
                    .link(siteForm.getLink())
                    .logo(site.getLogo()).build();

            profileSiteRepository.save(profileSite);
            returnList.add(result);
        }

        // Return


        return returnList;
    }

}
