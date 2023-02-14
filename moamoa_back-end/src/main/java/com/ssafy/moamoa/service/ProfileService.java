package com.ssafy.moamoa.service;

import com.ssafy.moamoa.domain.ProfileOnOffStatus;
import com.ssafy.moamoa.domain.ProfileSearchStatus;
import com.ssafy.moamoa.domain.dto.ContextForm;
import com.ssafy.moamoa.domain.dto.ProfileForm;
import com.ssafy.moamoa.domain.dto.ProfilePageForm;
import com.ssafy.moamoa.domain.dto.ProfileSearchStatusForm;
import com.ssafy.moamoa.domain.entity.Profile;
import com.ssafy.moamoa.domain.entity.User;
import com.ssafy.moamoa.repository.ProfileRepository;
import com.ssafy.moamoa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class ProfileService {
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";

    private final TechStackService techStackService;

    private final ReviewService reviewService;

    private final SideProjectService sideProjectService;

    private final SiteService siteService;

    private final AreaService areaService;

    private final S3Service s3Service;


    private final ProfileRepository profileRepository;

    private final UserRepository userRepository;


    public ProfilePageForm getProfile(Long profileId) {


        Profile profile = profileRepository.getProfileById(profileId);
        log.info(profile.getNickname());

        ProfileForm profileForm = ProfileForm.builder()
                .id(profile.getId())
                .nickname(profile.getNickname())
                .context(profile.getContext())
                .img(profile.getImg())
                .profileOnOffStatus(profile.getProfileOnOffStatus() + "")
                .profileSearchStatus(profile.getSearchState() + "")
                .hit(profile.getHit())
                .build();


        ProfilePageForm profilePageForm = ProfilePageForm.builder()
                .profileForm(profileForm)
                .techStackFormList(techStackService.getProfileTechStacks(profileId))
                .reviewFormList(reviewService.getReviews(profileId))
                .sidePjtFormList(sideProjectService.getSideProjects(profileId))
                .siteFormList(siteService.getProfileSites(profileId))
                .areaList(areaService.getProfileAreas(profileId))
                .build();

        return profilePageForm;
    }

    public ProfilePageForm modifyProfile(Long profileId, ProfilePageForm profilePageForm, MultipartFile file) throws IOException {
        // Profile
        Profile profile = profileRepository.getProfileById(profileId);
        ProfileForm profileForm = profilePageForm.getProfileForm();
        Profile inputProfile = Profile.builder()
                .id(profileId)
                .profileOnOffStatus(ProfileOnOffStatus.valueOf(profileForm.getProfileOnOffStatus()))
                .nickname(profilePageForm.getProfileForm().getNickname())
                .build();

        String output = s3Service.uploadProfileImg(profileId, file, profileForm.getNickname());

        // Profile
        profile.setProfileOnOffStatus(inputProfile.getProfileOnOffStatus());
        if (inputProfile.getNickname() != null) {
            profile.setNickname(inputProfile.getNickname());
        }
        profile.setImg(output);


        ProfilePageForm returnForm = ProfilePageForm.builder()
                .profileForm(profileForm)
                .techStackFormList(techStackService.modifyProfileTechStack(profileId, profilePageForm.getTechStackFormList()))
                .reviewFormList(reviewService.getReviews(profileId))
                .sidePjtFormList(sideProjectService.getSideProjects(profileId))
                .siteFormList(siteService.modifyProfileSite(profileId, profilePageForm.getSiteFormList()))
                .areaList(areaService.modifyProfileAreas(profileId, profilePageForm.getAreaList()))
                .build();


        return returnForm;
    }

    public ProfileSearchStatusForm changeUserSearchState(Long profileId) {

        Profile profile = profileRepository.findById(profileId).get();

        ProfileSearchStatus originSearchState = profile.getSearchState();
        String searchState = String.valueOf(originSearchState);
        switch (searchState) {
            case "ALL":
                originSearchState = ProfileSearchStatus.PROJECT;
                break;
            case "PROJECT":
                originSearchState = ProfileSearchStatus.STUDY;
                break;
            case "STUDY":
                originSearchState = ProfileSearchStatus.NONE;
                break;
            case "NONE":
                originSearchState = ProfileSearchStatus.ALL;
                break;
        }
        profile.setSearchState(originSearchState);
        profileRepository.save(profile);

        return ProfileSearchStatusForm.builder().id(profile.getId()).status(originSearchState.toString()).build();
    }

    public boolean checkDeletedUser(Long profileId) {
        User user = profileRepository.getUserByProfileId(profileId);
        if (user.isLocked()) {
            return false;
        }

        return true;
    }


    public ContextForm addContext(Long profileId, String context) {
        Profile profile = profileRepository.getProfileById(profileId);
        profile.setContext(context);

        profileRepository.save(profile);

        return ContextForm.builder().Context(context).build();
    }

    public String deleteContext(Long profileId) {
        profileRepository.deleteProfileContextById(profileId);
        Profile profile = profileRepository.getProfileById(profileId);

        if (profile.getContext() == null) {
            return SUCCESS;
        } else
            return FAIL;

    }

    public void deleteUser(Long userId) {
        User user = userRepository.getUserById(userId);
        user.setLocked(true);

    }

    public Profile profileByUserId(Long userId) {
        return profileRepository.getProfileByUserId(userId);

    }

    // 프로필 조회수 증가 : 본인이거나, 로그인 하지 않은 사람이면 증가하지 않음.
    public void addProfileHit(Long profileId, Authentication authentication) {

        UserDetails userDetails = null;

        Profile loggedInProfile = null;
        if (authentication != null) {
            userDetails = (UserDetails) authentication.getPrincipal();
            loggedInProfile = profileRepository.getProfileByUserId(Long.valueOf(userDetails.getUsername()));
            if (loggedInProfile.getId() == profileId) {
                return;
            }
        }


        Profile profile = profileRepository.getProfileById(profileId);

        profile.setHit(profile.getHit() + 1);

        profileRepository.save(profile);

    }

}