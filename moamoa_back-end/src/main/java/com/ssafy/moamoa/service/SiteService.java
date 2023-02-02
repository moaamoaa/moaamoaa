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
import java.util.List;
import java.util.Optional;

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


    public List<SiteForm> modifySite(Long profileId, List<SiteForm> siteFormList)
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
